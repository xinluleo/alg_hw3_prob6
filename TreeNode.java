/**
 * Created by xin on 10/13/14.
 */
public class TreeNode {
    int bday;                       //bday: birthday, the key of a node;
    int count;                      //count: number of people with the same birthday 'bday'
    double avgSal;                  //average salary of people with same birthday 'bday'
    int min_d, max_d;               //minimum bday and maximum bday in subtree rooted at this node
    int leftCount, rightCount;      //count of left descendant and right descendant
    double leftAvgSal, rightAvgSal; //average salary of left descendant and right descendant
    TreeNode left;                  //pointer to left child
    TreeNode right;                 //pointer to right child

    TreeNode(int d, double s) {
        bday = d;
        avgSal = s;
        count = 1;
        left = null;
        right = null;
        max_d = min_d = d;
        leftCount = rightCount = 0;
        leftAvgSal = rightAvgSal = 0;
    }
}
