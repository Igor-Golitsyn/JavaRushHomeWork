package com.javarush.test.level30.lesson06.home01;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
/**
 * Created by golit on 18.03.2016.
 */
public class BinaryRepresentationTask extends RecursiveTask<String> {
    private final int inputInteger;
    public BinaryRepresentationTask(int i) {
        this.inputInteger = i;
    }
    @Override
    protected String compute() {
        List<BinaryRepresentationTask> binaryRepresentationTaskList=new ArrayList<>();
        int a = inputInteger % 2;
        String rezult=String.valueOf(a);
        int b = inputInteger / 2;
        if (b>0){
            BinaryRepresentationTask task=new BinaryRepresentationTask(b);
            task.fork();
            binaryRepresentationTaskList.add(task);
        }
        for (BinaryRepresentationTask task:binaryRepresentationTaskList){
            rezult=task.join()+rezult;
        }
        return rezult;
    }
}
