package com.alibaba.mos.util.structure;


/**
 * @ProjectName: structure
 * @Package: heap
 * @ClassName: BigCapHeap
 * @Author: zwj
 * @Description: 注释 大顶堆
 */
public class BigCapHeap {

    /**
     * 堆是一种完全二叉树，所以使用数组来实现可以更好的利用资源，可以单纯的使用数组下标来找到一个节点的左右子节点和父节点
     */
    private CData[]a; //数组,从下标1开始存储数据
    private int n;//堆可以存储的最大数据个数
    private int count; // 堆中已存在的数据个数

    public BigCapHeap(int capacity){
        a = new CData[capacity+1];
        n = capacity;
        count = 0;
    }

    public void insert(CData data){
        if(count!=0){
            if(count>=n){
                remvoeMax();
            }; // 堆满了
        }

        ++count;
        a[count]=data;
        int i = count;
        while(i/2 > 0 && a[i].inventory<a[i/2].inventory){ // 自下往上堆化,父节点下标=节点下标/2
            swap(a,i,i/2);// swap()函数的作用，交换下标为i和i/2的两个元素
            i = i/2;
        }
     }


     public void remvoeMax(){
        if(count == 0)return; // 堆中没有元素
         a[1] = a[count]; // 删除对底，将堆尾放入对顶的位置
         a[count] = null;
         --count;
         heapify(a,count,1); //开始堆化，要满足堆的两种规定
     }

     private void heapify(CData[]a,int n,int i){ // 自上往下堆化
        while(true){
            int maxPos = i;
            if(i*2<=n && a[i].inventory>a[i*2].inventory){ // 先与左子节点比较
                maxPos = i*2;
            }
            if(i*2+1<=n && a[maxPos].inventory>a[i*2+1].inventory){ // 再与右子节点比较
                maxPos = i*2+1;
            }
            if(maxPos==i){
                break;
            }
            swap(a,i,maxPos);
            i = maxPos;
        }
     }


    /**
     *
     * @param a
     * @param i 关注节点下标
     * @param pi 关注节点的父节点/子节点下标
     */
    public void swap(CData[]a,int i,int pi){
        CData j = a[i];
        a[i] = a[pi];
        a[pi] = j;
    }


    public String getA(){
        if(a.length>0){
            StringBuffer sbf = new StringBuffer();
            for(int i = 1;i<a.length;i++){
                sbf.append(a[i].getId()).append(" ");
            }
            return sbf.toString();
        }
        return null;
    }

    public static class CData{

        String id;
        Double inventory;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Double getInventory() {
            return inventory;
        }

        public void setInventory(Double inventory) {
            this.inventory = inventory;
        }
    }
}
