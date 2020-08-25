import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class mapStruct {
    String word;
    int val;
    public mapStruct(String word, int val) {
        this.val = val;
        this.word = word;
    }
}
public class wordCount {

    List<String> details = new ArrayList<>();
    List<mapStruct> mapLists = Collections.synchronizedList(new ArrayList<>());
    Map<String, List<Integer>> shuffleMap = new ConcurrentHashMap<>();
    Map<String, Integer> reduceMap = new ConcurrentHashMap<>();
    CountDownLatch mapLatch;
    CountDownLatch shuffleLatch = new CountDownLatch(5);
    CountDownLatch reduceLatch = new CountDownLatch(5);
    ExecutorService pool = Executors.newCachedThreadPool();

    /* 加载全部数据，每1000行作为一份数据块，存储在details中 */
    public void readFile(String filename){
        try {
            int num = 1;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream
                    (new File(filename)), "UTF-8"));
            String line;
            String blockLine = "";
            while ((line = br.readLine()) != null) {
                blockLine = blockLine + line;
                if( num % 1000 == 0  ){
                    details.add(blockLine);
                }
                num = num + 1;
            }
            details.add(blockLine);
            mapLatch = new CountDownLatch(details.size());
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }
    }

    /*每份数据块，新建线程进行split和map操作*/
    public void splitandMap() throws InterruptedException{

        for (int i =0;i<details.size();i++){
            //pool.execute(new splitandMapThread(details.get(i),mapLists, shuffleLatch));
            String line = details.get(i);
            pool.execute(()->{
                String[] sArr = line.split("[^a-zA-Z0-9]+");
                for (int j = 0; j<sArr.length;j++) {
                    mapLists.add(new mapStruct(sArr[j].toLowerCase(), 1));
                }
                mapLatch.countDown();
            });
        }
        mapLatch.await();
    }

    /*shuffle阶段，分成5块,使用5个线程进行处理*/
    public void shuffle() throws InterruptedException{

        //根据word进行排序
        mapLists.sort(new Comparator<mapStruct>() {
            @Override
            public int compare(mapStruct o1, mapStruct o2) {
                return o1.word.compareTo(o2.word);
            }
        });

        int blocks = 5;
        int blockSize = mapLists.size() / blocks;
        for (int i = 0;i<mapLists.size();i++) {
            if ( i % blockSize == 0){
                List<mapStruct> tmp ;
                if (i + blockSize < mapLists.size()){
                    tmp = mapLists.subList(i, i+blockSize);
                } else {
                    tmp = mapLists.subList(i, mapLists.size());
                }
                pool.execute(()->{
                    for (int j =0 ;j < tmp.size(); j++){
                        mapStruct map = tmp.get(j);
                        if (shuffleMap.containsKey(map.word)) {
                            shuffleMap.get(map.word).add(map.val);
                        } else {
                            List<Integer> list = new ArrayList<>();
                            list.add(map.val);
                            shuffleMap.put(map.word, list);
                        }
                    }
                    shuffleLatch.countDown();
                });

            }
        }
        shuffleLatch.await();
    }

    /*分5份多线程进行reduce操作*/
    public void reduce() throws InterruptedException{
        int thread_num = 5;
        List<String> words = new ArrayList<>(shuffleMap.keySet());

        for (int i = 0;i<thread_num;i++) {
            List<String> subWord = words.subList(i*words.size()/thread_num,(i+1)*words.size()/thread_num);
            Map<String,Integer> tmp = new HashMap<>();
            pool.execute(()->{
                for(String key:subWord) {
                    tmp.put(key,shuffleMap.get(key).size());
                }
                reduceMap.putAll(tmp);
                reduceLatch.countDown();
            });
        }
        reduceLatch.await();

    }

    public void writeFile(String writePath) {
        Set set = reduceMap.keySet();
        Object[] arr=set.toArray();
        Arrays.sort(arr);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(writePath)) ;
            for(Object key:arr){
                String line = key+": "+reduceMap.get(key)+"\n";
                bw.write(line);
            }

            bw.flush();
            bw.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }
    }


    public static void main(String[] args) throws InterruptedException{
        long startTime = System.currentTimeMillis();

        wordCount wordCount = new wordCount();

        wordCount.readFile("D:\\IdeaProjects\\wordCount\\src\\hamlet.txt");


        wordCount.splitandMap();


        wordCount.shuffle();


        wordCount.reduce();

        wordCount.writeFile("wordCount.txt");

        long endTime = System.currentTimeMillis();

        System.out.println("多线程程序运行时间：" + (endTime - startTime) + "ms");
    }
}
