/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.*;
import entity.Class;
import entity.StudentClassDetail;
import entity.StudentTest;
import entity.StudentTestDetail;
import entity.Question;
import entity.Selection;
import entity.Subject;
import entity.Test;
import entity.TestDetail;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnect;
import utility.Utility;

/**
 *
 * @author Asus
 */
public class DAO extends DBConnect {

    public int setUser(User user) {
        //set new user to database
        int n = 0;
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[firstname]\n"
                + "           ,[lastname]\n"
                + "           ,[email]\n"
                + "           ,[time]\n"
                + "           ,[role]\n"
                + "           ,[confirmkey])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user.getUsername());
            pre.setString(2, Utility.encode(user.getPassword()));
            pre.setString(3, user.getFirstname());
            pre.setString(4, user.getLastname());
            pre.setString(5, user.getEmail());
            pre.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            pre.setInt(7, user.getRole());
            pre.setString(8, Utility.generateRandCode());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addClass(Class cls) {
        //add new class to database
        int n = 0;
        String sql = "INSERT INTO [dbo].[Class]\n"
                + "           ([name]\n"
                + "           ,[subjectid]\n"
                + "           ,[lectureid]\n"
                + "           ,[startclass]\n"
                + "           ,[finishedclass]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cls.getName());
            pre.setInt(2, cls.getSubjectId());
            pre.setInt(3, cls.getLectureId());
            pre.setDate(4, cls.getStartClass());
            pre.setDate(5, cls.getFinishedClass());
            pre.setInt(6, cls.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addSubject(Subject subject) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Subject]\n"
                + "           ([name]\n"
                + "           ,[numofchapter])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, subject.getName());
            pre.setInt(2, subject.getNumOfChapter());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addUser(User user) {
        int n = setUser(user);
        user = getUser(user.getEmail());
        int role = 0;
        switch (user.getRole()) {
            case 0:
                role = setAdmin(user.getId());
                break;
            case 1:
                role = setLecture(user.getId(), 1);
                break;
            case 2:
                role = setStudent(user.getId(), 1);
                break;
        }
        return (n > 0 && role > 0 ? 1 : 0);
    }

    public int addTest(Test test) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Test]\n"
                + "           ([name]\n"
                + "           ,[duration]\n"
                + "           ,[starttime]\n"
                + "           ,[finishedtime]\n"
                + "           ,[numofques]\n"
                + "           ,[coefficient]\n"
                + "           ,[classid]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = conn.prepareCall(sql);
            pre.setString(1, test.getName());
            pre.setInt(2, test.getDuration());
            pre.setTimestamp(3, test.getStartTime());
            pre.setTimestamp(4, test.getFinishedTime());
            pre.setInt(5, test.getNumOfQues());
            pre.setDouble(6, test.getCoefficient());
            pre.setInt(7, test.getClassId());
            pre.setInt(8, test.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addQuestion(Question question) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Question]\n"
                + "           ([subjectid]\n"
                + "           ,[description]\n"
                + "           ,[chapter])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, question.getSubjectId());
            pre.setString(2, question.getDescription());
            pre.setInt(3, question.getChapter());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public int addSelection(Selection selection) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Selection]\n"
                + "           ([questionid]\n"
                + "           ,[coefficient]\n"
                + "           ,[charid]\n"
                + "           ,[description])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, selection.getQuestionId());
            pre.setDouble(2, selection.getCoefficent());
            pre.setString(3, selection.getCharId());
            pre.setString(4, selection.getDescription());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public TestDetail getTestDetail(int id) {
        TestDetail testDetail = new TestDetail();
        String sql = "SELECT [id]\n"
                + "      ,[testid]\n"
                + "      ,[questionid]\n"
                + "      ,[coefficient]\n"
                + "  FROM [dbo].[Test_Detail] WHERE id = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                testDetail.setId(rs.getInt("id"));
                testDetail.setQuestionId(rs.getInt("questionid"));
                testDetail.setTestId(rs.getInt("testid"));
                testDetail.setCoefficient(rs.getDouble("coefficient"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return testDetail;
    }

    public int setStudentTestDetail(StudentTestDetail ctd) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Student_Test_Detail]\n"
                + "           ([STid]\n"
                + "           ,[TDid]\n"
                + "           ,[selected]\n"
                + "           ,[score])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ctd.getSTid());
            pre.setInt(2, ctd.getTDid());
            pre.setString(3, ctd.getSelected());
            pre.setDouble(4, ctd.getScore());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int setTestDetail(TestDetail testDetail) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Test_Detail]\n"
                + "           ([testid]\n"
                + "           ,[questionid]\n"
                + "           ,[coefficient])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, testDetail.getTestId());
            pre.setInt(2, testDetail.getQuestionId());
            pre.setDouble(3, testDetail.getCoefficient());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int setStudentTest(StudentTest st) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Student_Test]\n"
                + "           ([SCDid]\n"
                + "           ,[Testid]\n"
                + "           ,[starttime]\n"
                + "           ,[finishedtime]\n"
                + "           ,[totalscore])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, st.getSCDId());
            pre.setInt(2, st.getTestId());
            pre.setTimestamp(3, st.getStartTime());
            pre.setTimestamp(4, st.getFinishedTime());
            pre.setDouble(5, st.getTotalScore());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int setStudentClassDetail(StudentClassDetail ccd) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Student_Class_Detail]\n"
                + "           ([studentid]\n"
                + "           ,[Classid]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ccd.getStudentId());
            pre.setInt(2, ccd.getClassId());
            pre.setInt(3, ccd.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int setStudent(int userid, int status) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Student]\n"
                + "           ([userid]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userid);
            pre.setInt(2, status);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int setLecture(int userid, int status) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Lecture]\n"
                + "           ([userid]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userid);
            pre.setInt(2, status);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int setAdmin(int userid) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[ADMIN]\n"
                + "           ([userid])\n"
                + "     VALUES\n"
                + "           (?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int changePassword(boolean confirm, String email, String newPassword, String confirmkey) {
        if (!confirm) {
            return 0;
        }
        int n = 0;
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [password] = '" + Utility.encode(newPassword) + "',[confirmkey]='" + confirmkey + "' WHERE [User].email = '" + email + "'";

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Class> getClass(User user) {
        Vector<Class> classes = new Vector<>();

        if (user.getRole() == 0) {
            return null;
        }
        String sql = "";
        if (user.getRole() == 1) {
            sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[subjectid]\n"
                    + "      ,[expertid]\n"
                    + "      ,[startclass]\n"
                    + "      ,[finishedclass]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Class] WHERE [expertid] = \n"
                    + "  (select [id] from Lecture where [userid] = " + user.getId() + ")";
        }

        if (user.getRole() == 2) {
            sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[subjectid]\n"
                    + "      ,[expertid]\n"
                    + "      ,[startclass]\n"
                    + "      ,[finishedclass]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Class] WHERE [id] = \n"
                    + "  (select [classid] from Student_Class_Detail where studentid = \n"
                    + "  (select [id] from Student where userid = " + user.getId() + ")\n"
                    + "  )";
        }

        ResultSet rs = this.getData(sql);

        try {
            while (rs.next()) {
                Class cls = new Class();
                cls.setId(rs.getInt("id"));
                cls.setName(rs.getString("name"));
                cls.setSubjectId(rs.getInt("subjectid"));
                cls.setLectureId(rs.getInt("lectureid"));
                cls.setStartClass(Date.valueOf(rs.getString("startclass")));
                cls.setStartClass(Date.valueOf(rs.getString("finishedclass")));
                cls.setStatus(rs.getInt("status"));
                classes.add(cls);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return classes;
    }

    public Vector<Question> getQuestion(int numOfQues, int subjectId, int lowerLimit, int upperLimit) {
        Vector<Question> questions = new Vector<>();
        String sql = "SELECT TOP(" + numOfQues + ") [id]\n"
                + "      ,[subjectid]\n"
                + "      ,[description]\n"
                + "      ,[chapter]\n"
                + "  FROM [dbo].[Question] WHERE  subjectid = " + subjectId + " and (chapter >= " + lowerLimit + " and chapter <= " + upperLimit + ")\n"
                + "  ORDER BY NEWID()";

        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                Question question = new Question();
                question.setSubjectId(rs.getInt("subjectid"));
                question.setId(rs.getInt("id"));
                question.setDescription(rs.getString("description"));
                question.setChapter(rs.getInt("chapter"));
                questions.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    public Vector<Selection> getAnswer(int questionid) {
        Vector<Selection> selections = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[questionid]\n"
                + "      ,[coefficient]\n"
                + "      ,[charid]\n"
                + "      ,[description]\n"
                + "  FROM [dbo].[Selection] WHERE [coefficient] > 0 and [questionid] = " + questionid;

        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                Selection selection = new Selection();
                selection.setId(rs.getInt("id"));
                selection.setQuestionId(questionid);
                selection.setCoefficent(rs.getDouble("coefficient"));
                selection.setCharId(rs.getString("charid"));
                selection.setDescription(rs.getString("description"));
                selections.add(selection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selections;
    }

    public Vector<Selection> getSelection(int questionid) {
        Vector<Selection> selections = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[questionid]\n"
                + "      ,[coefficient]\n"
                + "      ,[charid]\n"
                + "      ,[description]\n"
                + "  FROM [dbo].[Selection] WHERE [questionid] = " + questionid;

        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                Selection selection = new Selection();
                selection.setId(rs.getInt("id"));
                selection.setQuestionId(questionid);
                selection.setCoefficent(rs.getDouble("coefficient"));
                selection.setCharId(rs.getString("charid"));
                selection.setDescription(rs.getString("description"));
                selections.add(selection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selections;
    }

    public Student getStudent(int userid) {
        Student student = new Student();
        String sql = "SELECT Student.id as 'sid', Student.userid, username, [password], firstname, lastname, email, [time], [role], [status]\n"
                + "from [User] inner join Student on [User].id = Student.userid and [User].id = " + userid;

        ResultSet rs = this.getData(sql);
        try {
            if (rs.next()) {
                student.setId(rs.getInt("sid"));
                student.setUserid(rs.getInt("userid"));
                student.setUsername(rs.getString("username"));
                student.setPassword(Utility.decode(rs.getString("password")));
                student.setFirstname(rs.getString("firstname"));
                student.setLastname(rs.getString("lastname"));
                student.setEmail(rs.getString("email"));
                student.setCreateTime(Timestamp.valueOf(rs.getString("time")));
                student.setRole(rs.getInt("role"));
                student.setStatus(rs.getInt("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    public Lecture getLecture(int userid) {
        Lecture lecture = new Lecture();
        String sql = "SELECT [Lecture].id as 'lid', [Lecture].userid, username, [password], firstname, lastname, email, [time], [role], [status]\n"
                + "from [User] inner join [Lecture] on [User].id = [Lecture].userid and [User].id = " + userid;

        ResultSet rs = this.getData(sql);
        try {
            if (rs.next()) {
                lecture.setId(rs.getInt("lid"));
                lecture.setUserid(rs.getInt("userid"));
                lecture.setUsername(rs.getString("username"));
                lecture.setPassword(Utility.decode(rs.getString("password")));
                lecture.setFirstname(rs.getString("firstname"));
                lecture.setLastname(rs.getString("lastname"));
                lecture.setEmail(rs.getString("email"));
                lecture.setCreateTime(Timestamp.valueOf(rs.getString("time")));
                lecture.setRole(rs.getInt("role"));
                lecture.setStatus(rs.getInt("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lecture;
    }

    public Admin getAdmin(int userid) {
        Admin admin = new Admin();
        String sql = "SELECT [ADMIN].id as 'aid', [ADMIN].userid, username, [password], firstname, lastname, email, [time], [role]\n"
                + "from [User] inner join [ADMIN] on [User].id = [ADMIN].userid and [User].id = " + userid;

        ResultSet rs = this.getData(sql);
        try {
            if (rs.next()) {
                admin.setId(rs.getInt("aid"));
                admin.setUserid(rs.getInt("userid"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(Utility.decode(rs.getString("password")));
                admin.setFirstname(rs.getString("firstname"));
                admin.setLastname(rs.getString("lastname"));
                admin.setEmail(rs.getString("email"));
                admin.setCreateTime(Timestamp.valueOf(rs.getString("time")));
                admin.setRole(rs.getInt("role"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }


    public User getUser(String... args) {
        User user = new User();

        String sql = "SELECT [id]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[firstname]\n"
                + "      ,[lastname]\n"
                + "      ,[email]\n"
                + "      ,[time]\n"
                + "      ,[role]\n"
                + "      ,[confirmkey]\n"
                + "  FROM [dbo].[User] WHERE " + (args.length == 2
                        ? "[username] = '" + args[0] + "' and [password] = '" + Utility.encode(args[1]) + "'"
                        : (Utility.isNumber(args[0]) ? "id = " + args[0] : "[email] = '" + args[0] + "'"));

        ResultSet rs = this.getData(sql);
        try {
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(Utility.decode(rs.getString("password")));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setCreateTime(Timestamp.valueOf(rs.getString("time")));
                user.setRole(rs.getInt("role"));
                user.setConfirmkey(rs.getString("confirmkey"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    public StudentTestDetail getStudentTestDetail(int STid, int TDid) {
        StudentTestDetail ctd = new StudentTestDetail();
        String sql = "SELECT [id]\n"
                + "      ,[CTid]\n"
                + "      ,[TDid]\n"
                + "      ,[selected]\n"
                + "      ,[score]\n"
                + "  FROM [dbo].[Student_Test_Detail] WHERE STid = " + STid + " and TDid = " + TDid;
        
        ResultSet rs = this.getData(sql);
        try {
            if (rs.next()) {
                ctd.setId(rs.getInt("id"));
                ctd.setId(rs.getInt("STid"));
                ctd.setId(rs.getInt("TDid"));
                ctd.setSelected(rs.getString("selected"));
                ctd.setScore(rs.getDouble("score"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ctd;
    }

    public Vector<Question> getTestQuestion(int testid) {
        Vector<Question> questions = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[subjectid]\n"
                + "      ,[description]\n"
                + "      ,[chapter]\n"
                + "  FROM [dbo].[Question] WHERE id = \n"
                + "  (select [questionid] from Test_Detail where testid = " + testid + ")";
        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                Question question = new Question();
                question.setSubjectId(rs.getInt("subjectid"));
                question.setId(rs.getInt("id"));
                question.setDescription(rs.getString("description"));
                question.setChapter(rs.getInt("chapter"));
                questions.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    public List<Question> loadQuestion(int size, int subjectId, int chap) {
        List<Question> list = new ArrayList<>();
        ResultSet rs = this.getData("select * from Question where subjectid =" + subjectId
                + (chap == 0 ? "" : "and chapter =" + chap));
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int subjectid = rs.getInt("subjectid");
                String description = rs.getString("description");
                int chapter = rs.getInt("chapter");
                list.add(new Question(id, subjectid, description, chapter));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.shuffle(list);
        if (list.size() < size) {
            return list;
        }
        return list.subList(0, size);
    }

    public Map<Question, List<Selection>> loadSelection(List<Question> ques) {
        Map<Question, List<Selection>> test = new HashMap<>();
        for (Question question : ques) {
            List<Selection> list = new ArrayList<>();
            String sql = "select * from selection where questionid = ? order by charid asc";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, question.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int quesid = rs.getInt("questionid");
                    double coff = rs.getDouble("coefficient");
                    String description = rs.getString("description");
                    String charId = rs.getString("charId");
                    list.add(new Selection(id, quesid, coff, description, charId));
                }
            } catch (SQLException e) {
                System.out.println("Error at load selection");
                System.out.println(e.getMessage());
            }
            test.put(question, list);
        }
        return test;
    }

    public void listAll(Vector list) {
        for (int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        DAO dao = new DAO();

        int n = 0;

//       n = dao.addUser(new User("khoinm", "abc", "Khoi", "Ngo", "khoinmhe176768@fpt.edu.vn", 0));
//        n = dao.addUser(new User("loveu1", "abc", "A", "Nguyen", "tuannmhe179004@fpt.edu.vn", 1));
//        n = dao.addUser(new User("anhcoczang", "abc", "B", "Nguyen", "tridungcvp2k3@gmail.com", 2));
//        n = dao.addSubject(new Subject("SWP391", 12));
//        n = dao.addClass(new Class("SWP391-SE1702.NET", 1, 1, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + (long) 30*24*3600*1000), 1));
//        n = dao.setStudentClassDetail(new StudentClassDetail(1, 1, 1));
//        n = dao.addTest(new Test("Quiz1", 3600, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 3600*1000), 1, 0, 1, 1));
//        n = dao.setStudentTest(new StudentTest(1, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 1200*1000), 10));
//        n = dao.addQuestion(new Question(1, "1 + 1 ? ", 2));
//        n = dao.setTestDetail(new TestDetail(1, 1, 1));
//        n = dao.setStudentTestDetail(new StudentTestDetail(1, 1, "B", 10));
//        for (int i = 0; i < 4; ++i) {
//            n = dao.addSelection(new Selection(1, (i == 1 ? 1 : 0), String.valueOf(i), String.valueOf((char)(i+'A'))));
//        }
//        System.out.println(dao.getUser("tridungtin8@gmail.com"));
//        System.out.println(dao.getAnswer(1));
//        List<Question> list = dao.loadQuestion(3, 1, 0);
//        System.out.println(list.get(0).getId());
//        Map<Question, List<Selection>> test = dao.loadSelection(list);
//        System.out.println(test.size());
//        for (Map.Entry<Question, List<Selection>> entry : test.entrySet()) {
//            System.out.println(entry.getKey());
//            for (Selection select : entry.getValue()) {
//                System.out.println(select);
//            }
//        }
//
//        dao.listAll(dao.getSelection(1));
//       System.out.println(dao.getStudentTestDetail(1, 1));
    }
}
