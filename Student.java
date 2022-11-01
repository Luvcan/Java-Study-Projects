public class Student {
    private String sid;
    private String sname;
    private int sage;
    private String saddress;

    public Student() {
    }

    public Student(String sid, String sname, int sage, String saddress) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.saddress = saddress;
    }

    /**
     * 获取
     * @return sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * 设置
     * @param sid
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 获取
     * @return sname
     */
    public String getSname() {
        return sname;
    }

    /**
     * 设置
     * @param sname
     */
    public void setSname(String sname) {
        this.sname = sname;
    }

    /**
     * 获取
     * @return sage
     */
    public int getSage() {
        return sage;
    }

    /**
     * 设置
     * @param sage
     */
    public void setSage(int sage) {
        this.sage = sage;
    }

    /**
     * 获取
     * @return saddress
     */
    public String getSaddress() {
        return saddress;
    }

    /**
     * 设置
     * @param saddress
     */
    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String toString() {
        return "Student{sid = " + sid + ", sname = " + sname + ", sage = " + sage + ", saddress = " + saddress + "}";
    }
}
