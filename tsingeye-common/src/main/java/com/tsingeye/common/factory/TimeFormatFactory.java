package com.tsingeye.common.factory;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

@Component
public class TimeFormatFactory {

    private TimeFormatFactory(){ }

    private static LinkedList<SimpleDateFormat> simpleDateFormats = new LinkedList<>();

    @PostConstruct
    public void  init(){
        for (int i=0;i<16;i++){
            simpleDateFormats.add(new SimpleDateFormat("yyyyMMddHHmmss"));
        }
    }

    public synchronized static SimpleDateFormat getSimpleDateFormat(){
        Boolean flag = true;
        SimpleDateFormat simpleDateFormat = null;
        while (flag){
            if (simpleDateFormats.size() > 0){
                simpleDateFormat = simpleDateFormats.removeFirst();
                flag = false;
            }else {
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        return simpleDateFormat;
    }

    public synchronized static void addSimpleDateFormat(SimpleDateFormat simpleDateFormat){
        simpleDateFormats.add(simpleDateFormat);
    }
}
