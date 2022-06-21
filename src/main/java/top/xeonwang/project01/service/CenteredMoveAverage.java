package top.xeonwang.project01.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.xeonwang.project01.annotation.AlgMethod;
import top.xeonwang.project01.model.AlgRunable;
import top.xeonwang.project01.model.Cma;
import top.xeonwang.project01.annotation.Algorithm;
import top.xeonwang.project01.model.CommandID;
import top.xeonwang.project01.model.DNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/**
 * 中心移动平均算法
 *
 * @author Chen Q.
 */
@Algorithm
@Component
@Slf4j
public class CenteredMoveAverage extends AlgRunable {

    public CenteredMoveAverage() {
        this.commandID = CommandID.CENTERED_MOVE_AVERAGE.ordinal();
    }

    @AlgMethod
    public static LinkedList<DNode> alg(LinkedList<DNode> list, int time) {
        if (list.size() < time) {
            return new LinkedList<>();
        }
        //返回值
        LinkedList<DNode> ret = new LinkedList();

        int left = 0, right = 0;
        ListIterator<DNode> iter = null;
        ListIterator<DNode> retTime = null;
        Double sum = 0.0, aver = 0.0;

        if (time % 2 == 1) { //奇数情形
            while (right < list.size()) {
                sum = 0.0;

                iter = list.listIterator(left); //O(n)

                right = left;
                while (right - left < time && right < list.size()) {
                    sum += iter.next().getData();
                    right++;
                }
                aver = sum / time;
                if (retTime == null) {
                    int i = (left + right) / 2;
                    retTime = list.listIterator(i);
                }

                ret.add(new DNode(retTime.next().getDate(), aver));

                if (right == list.size()) {
                    break;
                }

                left++;
            }
        } else { // 偶数情形
            while (right < list.size()) {
                right = left;

                //计算时间
                int i = 0;
                //第一轮循环
                sum = 0.0;

                //TODO 优化
                iter = list.listIterator(left);

                while (right - left < time && right < list.size()) {
                    sum += iter.next().getData();
                    right++;
                }
                aver = sum / time;

                i += left + right;

                //第二轮循环
                sum = 0.0;
                int temp = left + 1;
                right = temp;
                iter = list.listIterator(temp);
                while (right - temp < time && right < list.size()) {
                    sum += iter.next().getData();
                    right++;
                }

                aver += sum / time;
                aver /= 2;

                i += temp + right;
                i /= 4;

                if (retTime == null) {
                    retTime = list.listIterator(i);
                }

                ret.add(new DNode(retTime.next().getDate(), aver));
                left++;
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        LinkedList<DNode> testData = new LinkedList<>();
        Random random = new Random();
        LocalDate of = LocalDate.of(1900, 1, 1);
        for (int i = 0; i < 10000; i++) {
            testData.add(new DNode(of, random.nextDouble()));
            of = of.plusDays(1);
        }

        String s = JSON.toJSONString(testData);

        File file = new File("E://file/1.json");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(s.getBytes(StandardCharsets.UTF_8));
        fileOutputStream.close();
//        testData.add(new DNode(LocalDate.of(2010, 1, 1), 100.0));
//        testData.add(new DNode(LocalDate.of(2010, 1, 2), 200.0));
//        testData.add(new DNode(LocalDate.of(2010, 1, 3), 300.0));
//        testData.add(new DNode(LocalDate.of(2010, 1, 4), 400.0));
//        testData.add(new DNode(LocalDate.of(2010, 1, 5), 500.0));
//        testData.add(new DNode(LocalDate.of(2010, 1, 6), 600.0));
//        testData.add(new DNode(LocalDate.of(2010, 1, 7), 700.0));
//        testData.add(new DNode(LocalDate.of(2010, 1, 8), 800.0));
//        testData.add(new DNode(LocalDate.of(2010, 1, 9), 900.0));
//        testData.add(new DNode(LocalDate.of(2010, 1, 10), 1000.0));


//        testData.add(new DNode(LocalDate.of(2010, 1, 2), random.nextDouble()));
//        testData.add(new DNode(LocalDate.of(2010, 1, 3), random.nextDouble()));
//        testData.add(new DNode(LocalDate.of(2010, 1, 4), random.nextDouble()));
//        testData.add(new DNode(LocalDate.of(2010, 1, 5), random.nextDouble()));
//
//        testData.add(new DNode(LocalDate.of(2010, 1, 6), random.nextDouble()));
//        testData.add(new DNode(LocalDate.of(2010, 1, 7), random.nextDouble()));
//        testData.add(new DNode(LocalDate.of(2010, 1, 8), random.nextDouble()));
//        testData.add(new DNode(LocalDate.of(2010, 1, 9), random.nextDouble()));
//        testData.add(new DNode(LocalDate.of(2010, 1, 10), random.nextDouble()));
//        long start = System.currentTimeMillis();
//        LinkedList<DNode> alg = alg(testData, 100);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//        System.out.println(alg.size());
    }

    //    @AlgMethod
    @Override
    public String run(String method, String params) {
        log.info("CENTERED_MOVE_AVERAGE");

        //TODO 泛型方法
//        this.getClass().getMethod(method, )
//        try {
//            Method cma = this.getClass().getMethod(method, LinkedList.class, int.class);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
        switch (method) {
            case "CenteredMoveAverage": {
                Cma cma = JSON.parseObject(params, Cma.class);
                LinkedList<DNode> alg = alg(cma.getList(), cma.getTime());
                return JSON.toJSONString(alg);
            }
        }
        return "";
    }
}
