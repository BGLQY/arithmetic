package day07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class EveryStepShowBoss02 {


    /**
     * 抽奖技巧法
     *
     * @param arr
     * @param op
     * @param k
     * @return
     */
    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        RequireDaddy requireDaddy = new RequireDaddy(k);
        for (int i = 0; i < arr.length; i++) {
            requireDaddy.operate(i, arr[i], op[i]);
            ans.add(requireDaddy.getOperate());
        }
        return ans;
    }


    private static class RequireDaddy {
        //用户关系
        private HashMap<Integer, Customer> map;

        // 等待区
        private HeapGreater<Customer> cands;

        // 获奖区
        private HeapGreater<Customer> daddy;

        // 界限
        private final int limit;


        public RequireDaddy(int limit) {
            map = new HashMap<Integer,Customer>();
            cands = new HeapGreater<>(new CandidateComparator());
            daddy = new HeapGreater<>(new DaddyComparator());
            this.limit = limit;
        }


        public void operate(int time, int id, boolean buyOrRefund) {
            if (!buyOrRefund && !map.containsKey(id)) {
                return;
            }

            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }

            Customer customer = map.get(id);
            if (buyOrRefund) {
                customer.buy++;
            } else {
                customer.buy--;
            }

            if (customer.buy == 0) {
                map.remove(id);
            }

            if (!cands.contains(customer) && !daddy.contains(customer)) {
                customer.enterTime = time;
                if (daddy.size() < limit) {
                    daddy.push(customer);
                } else {
                    cands.push(customer);
                }
            } else if (cands.contains(customer)) {
                if (customer.buy == 0) {
                    cands.remove(customer);
                } else {
                    cands.resign(customer);
                }
            } else {
                if (customer.buy == 0) {
                    daddy.remove(customer);
                } else {
                    daddy.resign(customer);
                }
            }
            daddyMove(time);
        }

        private void daddyMove(int time) {
            if (cands.isEmpty()) {
                return;
            }
            if (daddy.size() < limit) {
                Customer p = cands.pop();
                p.enterTime = time;
                daddy.push(p);
            } else {
                if (cands.peek().buy > daddy.peek().buy) {
                    Customer o1 = cands.pop();
                    Customer o2 = daddy.pop();
                    o1.enterTime = o2.enterTime = time;
                    daddy.push(o1);
                    cands.push(o2);
                }
            }

        }


        public List<Integer> getOperate() {
            List<Customer> allElements = daddy.getAllElements();
            List<Integer> ans = new ArrayList<>();
            for (Customer allElement : allElements) {
                ans.add(allElement.id);
            }
            return ans;
        }
    }


    /**
     * 抽奖 暴力法
     *
     * @param arr 用户
     * @param op  进退货
     * @param k   前k名用户
     * @return
     */
    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> map = new HashMap<>();
        // 等待区
        List<Customer> cands = new ArrayList<>();
        // 获奖区
        List<Customer> daddy = new ArrayList<>();
        // 结果
        List<List<Integer>> ans = new ArrayList<>();
        // 遍历时间线
        for (int i = 0; i < arr.length; i++) {
            // 用户id
            int id = arr[i];
            // 用户行为
            boolean buyOrRefund = op[i];

            // 当用户 没买过就发生退货行为
            if (!buyOrRefund && !map.containsKey(id)) {
                ans.add(getCurAns(daddy));
                continue;
            }
            // 此时可能发生买货也可能卖货  关系表中没有当前用户
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            // 用户的买卖行为
            Customer customer = map.get(id);
            if (buyOrRefund) {
                customer.buy++;
            } else {
                customer.buy--;
            }
            // 当用户退货退到为0
            if (customer.buy == 0) {
                map.remove(id);
            }
            // 进入 奖区 规则
            if (!cands.contains(customer) && !daddy.contains(customer)) {
                customer.enterTime = i;
                // 判断奖区是否满了
                if (daddy.size() < k) {
                    daddy.add(customer);
                } else {
                    cands.add(customer);
                }
            }
            // 将退货到0的用户移除
            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            // 候选区排序
            cands.sort(new CandidateComparator());
            // 得奖区排序
            daddy.sort(new DaddyComparator());
            // 将满足条件的候选区用户 移动到 获奖区
            move(cands, daddy, k, i);
            ans.add(getCurAns(daddy));
        }


        return ans;
    }

    private static void move(List<Customer> cands, List<Customer> daddy, int k, int time) {
        if (cands.size() == 0) {
            return;
        }
        if (daddy.size() < k) {
            Customer customer = cands.get(0);
            customer.enterTime = time;
            daddy.add(customer);
            cands.remove(customer);
        } else {
            if (cands.get(0).buy > daddy.get(0).buy) {
                Customer newCustomer = cands.get(0);
                Customer oldCustomer = daddy.get(0);
                daddy.remove(0);
                cands.remove(0);
                oldCustomer.enterTime = newCustomer.enterTime = time;
                daddy.add(newCustomer);
                cands.add(oldCustomer);
            }
        }

    }

    private static void cleanZeroBuy(List<Customer> list) {
        ArrayList<Customer> noZero = new ArrayList<>();
        for (Customer customer : list) {
            if (customer.buy != 0) {
                noZero.add(customer);
            }
        }
        list.clear();
        for (Customer customer : noZero) {
            list.add(customer);
        }
    }

    /**
     * 将获奖区迭代出来后返回
     *
     * @param daddy
     * @return
     */
    private static List<Integer> getCurAns(List<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer customer : daddy) {
            ans.add(customer.id);
        }
        return ans;
    }


    /**
     * 用户
     */
    public static class Customer {
        // 用户id
        public int id;
        // 用户 进退货
        public int buy;
        // 用户 进入时间
        public int enterTime;

        public Customer(int id, int buy, int enterTime) {
            this.id = id;
            this.buy = buy;
            this.enterTime = enterTime;
        }

    }

    private static class CandidateComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            // 谁买的多 谁的时间早
            return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
        }
    }

    private static class DaddyComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            // 谁买的少 谁排前面 谁来的早谁排前面
            return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
        }
    }


    // 为了测试
    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    // 为了测试
    public static Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Data(arr, op);
    }

    // 为了测试
    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort((a, b) -> a - b);
            cur2.sort((a, b) -> a - b);
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 10;
        int maxK = 6;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = compare(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }
}