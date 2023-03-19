package day03;

/**
 * @Author liangbaigao
 * @Date 2023/3/14 16:42
 */
public class ArrayImplementsSAndQ04 {

    /**
     * 数组实现队列  环形数组
     */
    public static class MyQueue {
        private int[] arr;

        private int polliRight;// end

        private int pushLeft;// begin
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            pushLeft = 0;
            polliRight = 0;
            size = 0;
            this.limit = limit;
        }

        /**
         * 入队
         * @param value
         */
        public void pushLeft(int value){
            if(size == limit){
                System.out.println("队列已满");
            }
            size++;
            arr[pushLeft] = value;
            pushLeft = nextIndex(pushLeft);
        }


        /**
         * 弹出
         * @return
         */
        public int popRight(){
            if(size == 0){
                System.out.println("队列为空");
            }
            size--;
            int value = arr[polliRight];
            polliRight = nextIndex(polliRight);
            return value;
        }

        /**
         * 判断是否为空
         * @return
         */
        public boolean isEmpty(){
            return size == 0;
        }

        /**
         * 防止越界
         */
        private int nextIndex(int value){
            return value < limit - 1 ? value + 1 : 0;
        }

    }


}
