
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StudentSystemPro {


    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        while (true) {
            System.out.println("———————————欢迎来到奇克学生管理系统————————————");
            System.out.println("1：登陆");
            System.out.println("2：注册");
            System.out.println("3：忘记密码");
            System.out.println("4：退出");
            System.out.println("请选择操作：");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case "1":
                    login(users);
                    break;
                case "2":
                    register(users);
                    break;
                case "3":
                    forget(users);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("输入错误！");
                    break;
            }
        }
    }

    //注册
    public static void register(ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        User newUser = new User();
        System.out.println("请输入您要注册的用户名：");
        while (true) {
            String userName = sc.next();
            if (!existUser(users, userName)) {
                System.out.println("该用户名已存在，请重新输入：");
            } else {
                if (affirmName(userName, newUser)) {
                    while (true) {
                        if (affirmPassword(newUser)) {
                            while (true) {
                                if (affirmId(newUser)) {
                                    while (true) {
                                        if (affirmPhone(newUser)) {
                                            users.add(newUser);
                                            System.out.println("注册成功！");
                                            return;
                                        } else {
                                            System.out.println("手机号输入非法！");
                                        }
                                    }
                                } else {
                                    System.out.println("身份证输入非法！");
                                }
                            }
                        } else {
                            System.out.println("两次密码输入不一致！");
                        }
                    }
                } else {
                    System.out.println("用户名输入非法，请重新输入：");
                }
            }
        }
    }


    //判断用户名是否唯一
    public static boolean existUser(ArrayList<User> users, String userName) {
        for (int i = 0; i < users.size(); i++) {
            String existName = users.get(i).getUserName();
            if (existName.equals(userName)) {
                return false;
            }
        }
        return true;
    }

    //判断用户名是否合法
    public static boolean affirmName(String userName, User usr) {
        if (userName.length() >= 3 && userName.length() <= 15) {
            if (!judgeNum(userName, userName.length())) {
                if (judge(userName, userName.length())) {
                    usr.setUserName(userName);
                    return true;
                }
            }
        }
        return false;
    }

    //判断密码是否正确
    public static boolean affirmPassword(User usr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的密码:");
        String password1 = sc.next();
        System.out.println("请确认您的密码:");
        String password2 = sc.next();
        if (password1.equals(password2)) {
            usr.setPassword(password1);
            return true;
        } else {
            return false;
        }
    }

    //判断身份证号是否合法
    public static boolean affirmId(User usr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的身份证号：");
        String newId = sc.next();
        if (newId.length() == 18) {
            if (newId.charAt(0) != '0') {
                if (judgeNum(newId, 17)) {
                    int sp = newId.charAt(17);
                    boolean flag = sp >= 48 && sp <= 57;
                    if (flag || sp == 'x' || sp == 'X') {
                        usr.setId(newId);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //判断手机号是否合法
    public static boolean affirmPhone(User usr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的手机号：");
        String newPhone = sc.next();
        if (newPhone.length() == 11) {
            if (newPhone.charAt(0) != '0') {
                if (judgeNum(newPhone, newPhone.length())) {
                    usr.setPhone(newPhone);
                    return true;
                }
            }
        }
        return false;
    }

    //判断字符串是否为纯数字
    public static boolean judgeNum(String str, int index) {
        for (int i = 0; i < index; i++) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57) {
                return false;
            }
        }
        return true;
    }

    //判断字符串是否为数字和字母结合
    public static boolean judge(String str, int index) {
        for (int i = 0; i < index; i++) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57 && chr <'A' || chr > 'Z' && chr < 'a' || chr > 'z' ) {
                return false;
            }
        }
        return true;
    }

    //登陆
    public static void login(ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入您的用户名：");
            String loginName = sc.next();
            int temp = contains(users, loginName);
            if (temp >= 0) {
                System.out.println("请输入您的密码：");
                String loginPassword = sc.next();
                String text = text();
                System.out.println("请输入验证码：" + text);
                String loginText = sc.next();
                if (loginText.equalsIgnoreCase(text)) {
                    for (int i = 0; i < 3; i++) {
                        if (users.get(temp).getPassword().equals(loginPassword)) {
                            System.out.println("登陆成功！");
                            manage();
                            return;
                        } else {
                            if (i < 2) {
                                System.out.println("密码错误，您还有" + (2 - i) + "次输入机会。");
                                System.out.println("请输入您的密码：");
                                loginPassword = sc.next();
                            }else {
                                System.out.println("您的登陆机会已耗尽，账户锁定。");
                            }
                        }
                    }break;
                } else {
                    System.out.println("验证码输入错误！请重新登录！");
                }
            } else{
                System.out.println("用户名未注册，请先注册！");
                return;
            }
        }
    }

    //判断用户名是否注册
    public static int contains(ArrayList<User> users, String loginName) {
//        System.out.println(users.size());
        if (users.size() != 0) {
            for (int i = 0; i < users.size(); i++) {
//                System.out.println(users.get(i).getUserName());
                if (users.get(i).getUserName().equals(loginName)) {
                    return i;
                }
            }
        }
        return -1;
    }

    //生成验证码
    public static String text() {
        int[] number = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        char[] letter = new char[52];
        char start = 'a';
        for (int i = 0; i < 26; i++) {
            letter[i] = start;
            start++;
        }
        start = 'A';
        for (int i = 26; i < 52; i++) {
            letter[i] = start;
            start++;
        }
        StringBuilder text = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(52);
            text.append(letter[index]);
        }

        int index = r.nextInt(5);
        int temp = r.nextInt(10);
        text.insert(index, number[temp]);

        return text.toString();
    }

    //忘记密码
    public static void forget (ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的用户名：");
        String forgetName = sc.next();
        if (contains(users, forgetName)<0) {
            System.out.println("该用户名未注册");
            return;
        }else {
            System.out.println("请输入您的身份证号码：");
            String forgetId = sc.next();
            System.out.println("请输入您的手机号码：");
            String forgetPhone = sc.next();
            int temp = containsIdPhone(users, forgetId, forgetPhone);
            if (temp>=0) {
                System.out.println("请输入修改后的密码：");
                String forgetPassword = sc.next();
                users.get(temp).setPassword(forgetPassword);
                System.out.println("修改成功！");
            }else {
                System.out.println("账号信息不匹配，修改失败。");
            }
        }
    }

    public static int containsIdPhone (ArrayList<User> users, String id, String phone) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id) && users.get(i).getPhone().equals(phone)) {
                return i;
            }
        }
        return -1;
    }


    public static void manage () {
        ArrayList<Student> students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("——————————欢迎来到奇克学生管理系统——————————");
            System.out.println("1：添加学生");
            System.out.println("2：删除学生");
            System.out.println("3：修改学生");
            System.out.println("4：查询学生");
            System.out.println("5：退出");
            System.out.println("请输入您的选择：");
            String choose = sc.next();
            switch (choose) {
                case "1":
                    addStudent(students);
                    break;
                case "2":
                    removeStudent(students);
                    break;
                case "3":
                    resetStudent(students);
                    break;
                case "4":
                    checkStudent(students);
                    break;
                case "5":
                    return;
            }
        }
    }

    //添加学生
    public static void addStudent (ArrayList<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要添加的学生id：");
        String addId = sc.next();
        if (containsId(students, addId) >= 0) {
            System.out.println("该id已存在!");
            return;
        }else {
            System.out.println("请输入要添加的学生姓名：");
            String addName = sc.next();
            System.out.println("请输入要添加的学生年龄：");
            int addAge = sc.nextInt();
            System.out.println("请输入要添加的学生地址：");
            String addAddress = sc.next();
            Student s = new Student(addId, addName, addAge, addAddress);
            students.add(s);
        }

    }

    //判断id唯一
    public static int containsId (ArrayList<Student> students, String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getSid().equals(id)) {
                return i;
            }
        }
        return -1;
    }


    //删除学生
    public static void removeStudent (ArrayList<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学生id：");
        String removeId = sc.next();
        if (containsId(students, removeId) >= 0) {
            int temp = containsId(students, removeId);
            students.remove(temp);
            System.out.println("删除成功！");
        }else {
            System.out.println("该id不存在！");
            return;
        }
    }

    //修改学生
    public static void resetStudent (ArrayList<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学生id：");
        String resetId = sc.next();
        if (containsId(students, resetId) >= 0) {
            int temp = containsId(students, resetId);
            System.out.println("请输入要修改的学生姓名：");
            String resetName = sc.next();
            System.out.println("请输入要修改的学生年龄：");
            int resetAge = sc.nextInt();
            System.out.println("请输入要修改的学生地址：");
            String resetAddress = sc.next();
            Student s = new Student(resetId, resetName, resetAge, resetAddress);
            students.set(temp,s);
            System.out.println("修改成功！");
        }else {
            System.out.println("该id不存在！");
            return;
        }

    }


    //查询学生
    public static void checkStudent (ArrayList<Student> students) {
        System.out.println("id\t\t姓名\t年龄\t地址");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getSid() + "\t" +
                    students.get(i).getSname() + "\t" +
                    students.get(i).getSage() + "\t" +
                    students.get(i).getSaddress());
        }
    }

}


