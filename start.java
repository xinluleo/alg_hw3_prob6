/**
 * Created by xin on 10/13/14.
 */
public class start {
    public static void main(String args[]) {
        int[] bday =      {6,3,2,4, 8,5,1,6,7,1,8,8};
        double[] salary = {0,1,1,10,8,8,2,2,5,9,1,3};
        TreeNode root = new TreeNode(bday[0], salary[0]);
        SalaryTree st = new SalaryTree();
        for (int i = 1; i < bday.length; i++) {
            st.insertNode(root, bday[i], salary[i]);
        }

        st.preorderPrint(root);

        st.deleteNode(root, 3, 1);
        System.out.println();
        st.preorderPrint(root);

        st.deleteNode(root, 1, 9);
        System.out.println();
        st.preorderPrint(root);

        st.deleteNode(root, 1, 2);
        System.out.println();
        st.preorderPrint(root);

        st.insertNode(root, 1, 2);
        System.out.println();
        st.preorderPrint(root);

        st.insertNode(root, 1, 9);
        System.out.println();
        st.preorderPrint(root);

        System.out.println("MinBday = " + st.MinBDay(root));

        int d = 7;
        int count_less_than_d = st.Count(root, d);
        System.out.println("Count of employees with bday <= " + d + ": " + count_less_than_d);

        double avg_sal_less_than_d = st.AvgSal(root, d);
        System.out.println("Average salary of employees with bday <= " + d + ": " + avg_sal_less_than_d);

        d = 4;
        int d_prime = 7;
        double avg_sal_in_range = st.AvgSalInRange(root, d, d_prime);
        System.out.println("Average salary of employees with bday in range (" + d + ", " + d_prime + "]: "
                + avg_sal_in_range);

    }
}
