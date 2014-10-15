/**
 * Created by xin on 10/13/14.
 */
public class SalaryTree {
    //Insert new node with birthday @bday and salary @salary
    //@node can not be null
    public void insertNode(TreeNode node, int bday, double salary){
        if (bday == node.bday) {
            node.avgSal = (node.avgSal * node.count + salary) / (node.count + 1);
            node.count++;
        } else if (bday < node.bday) {
            node.leftAvgSal = (node.leftAvgSal * node.leftCount + salary) / (node.leftCount + 1);
            node.leftCount++;
            node.min_d = Math.min(bday, node.min_d);
            node.max_d = Math.max(bday, node.max_d);
            if (node.left != null) {
                insertNode(node.left, bday, salary);
            } else {
                node.left = new TreeNode(bday, salary);
            }
        } else {
            node.rightAvgSal = (node.rightAvgSal * node.rightCount + salary) / (node.rightCount + 1);
            node.rightCount++;
            node.min_d = Math.min(bday, node.min_d);
            node.max_d = Math.max(bday, node.max_d);
            if (node.right != null) {
                insertNode(node.right, bday, salary);
            } else {
                node.right = new TreeNode(bday, salary);
            }
        }
    }

    //Assumption: node has been inserted before
    //Delete a node (@bday, @salary) from tree rooted at node
    public void deleteNode(TreeNode node, int bday, double salary) {
        if (node == null) {
            return;
        }

        if (bday == node.bday) {
            node.count--;
            if (node.count == 0) {
                node.avgSal = 0;
            } else {
                node.avgSal = (node.avgSal * (node.count + 1) - salary) / node.count;
            }
        } else if (bday < node.bday) {
            if (node.left != null) {
                node.leftCount--;
                if (node.leftCount == 0) {
                    node.leftAvgSal = 0;
                } else {
                    node.leftAvgSal = (node.leftAvgSal * (node.leftCount + 1) - salary) / node.leftCount;
                }
                deleteNode(node.left, bday, salary);
            }
        } else {
            if (node.right != null) {
                node.rightCount--;
                if (node.rightCount == 0) {
                    node.rightAvgSal = 0;
                } else {
                    node.rightAvgSal = (node.rightAvgSal * (node.rightCount + 1) - salary) / node.rightCount;
                }
                deleteNode(node.right, bday, salary);
            }
        }
    }

    public int MinBDay(TreeNode node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        return Math.min(node.count > 0 ? node.bday : Integer.MAX_VALUE, MinBDay(node.left));
    }

    public int Count(TreeNode root, int d) {
        return countInRange(root, Integer.MIN_VALUE, d);
    }

    public int countInRange(TreeNode node, int min_d, int max_d) {
        if (node == null || node.min_d > max_d || node.max_d < min_d) {
            return 0;
        }

        if (node.min_d >= min_d && node.max_d <= max_d) {
            return node.leftCount + node.rightCount + node.count;
        }

        int self_count = (node.bday >= min_d && node.bday <= max_d) ? node.count : 0;
        int new_min_d = Math.max(min_d, node.min_d);
        int new_max_d = Math.min(max_d, node.max_d);
        return countInRange(node.left, new_min_d, new_max_d)
                + countInRange(node.right, new_min_d, new_max_d)
                + self_count;
    }

    public double AvgSal(TreeNode root, int d) {
        return salSumInRange(root, Integer.MIN_VALUE, d) / Count(root, d);
    }

    public double AvgSalInRange(TreeNode root, int d, int d_prime) {
        return salSumInRange(root, d + 1, d_prime) / countInRange(root, d + 1, d_prime);
    }

    public double salSumInRange(TreeNode node, int min_d, int max_d) {
        if (node == null || node.min_d > max_d || node.max_d < min_d) {
            return 0;
        }

        if (node.min_d >= min_d && node.max_d <= max_d) {
            return node.leftAvgSal * node.leftCount + node.rightAvgSal * node.rightCount
                    + node.avgSal * node.count;
        }

        double self_avg_salary = (node.bday >= min_d && node.bday <= max_d) ? node.avgSal : 0;
        int new_min_d = Math.max(min_d, node.min_d);
        int new_max_d = Math.min(max_d, node.max_d);
        return salSumInRange(node.left, new_min_d, new_max_d)
                + salSumInRange(node.right, new_min_d, new_max_d)
                + self_avg_salary * node.count;
    }

    public void preorderPrint(TreeNode node){
        if (node != null) {
            System.out.println("(bday = " + node.bday + ", avgSal = " + node.avgSal + ", count = " + node.count
                    + ", max_d = " + node.max_d + ", min_d = " + node.min_d
                    + ", l_ch_num = " + node.leftCount + ", r_ch_num = " + node.rightCount
                    + ", l_ch_avgsal = " + node.leftAvgSal + ", r_ch_avgsal = " + node.rightAvgSal + ")");
            preorderPrint(node.left);
            preorderPrint(node.right);
        }
    }
}
