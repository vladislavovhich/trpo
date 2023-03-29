package lab1;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JList.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
public class Program
{
    public static JFrame frame;
    public static JButton addStudentBtn, saveStudentBtn, removeStudentBtn;
    public static JLabel isBudgetLabel, studentNameLabel, studentSurnameLabel, averageGradeLabel, hasPassedLabel;
    public static JTextField studentNameField, studentSurnameField, averageGradeField;
    public static JList studentsList;
    public static DefaultListModel model;
    public static JCheckBox hasPassedCheckBox, isBudgetBox;
    public static StudentRepo repo;
    public static Allowance allowance = new Allowance(100);
    public static int selectedItem = -1;

    public static void main(String[] args)
    {
         repo = new StudentRepo();

         frame = new JFrame("lab6");


         studentNameField = new JTextField(30);
         studentNameLabel = new JLabel("Student name: ");

         studentSurnameField = new JTextField(30);
         studentSurnameLabel = new JLabel("Student surname: ");

         averageGradeField = new JTextField(30);
         averageGradeLabel = new JLabel("Student grade: ");

         addStudentBtn = new JButton("Add student");
         saveStudentBtn = new JButton("Save student");
         removeStudentBtn = new JButton("Remove student");

         hasPassedCheckBox = new JCheckBox();
         hasPassedLabel = new JLabel("Passed exams: ");

          isBudgetBox = new JCheckBox();
         isBudgetLabel = new JLabel("Student is budget: ");
  
         studentsList = new JList();

         studentsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
         studentsList.setLayoutOrientation(JList.VERTICAL_WRAP);

         hasPassedCheckBox.setBounds(115, 80, 20, 20);
         hasPassedLabel.setBounds(10, 80, 100, 20);

         isBudgetBox.setBounds(115, 105, 20, 20);
         isBudgetLabel.setBounds(10, 105, 100, 20);

         studentsList.setBounds(240, 10, 500, 190);

         studentNameLabel.setBounds(10, 10, 110, 20);
         studentNameField.setBounds(115,10,120,20);

         studentSurnameLabel.setBounds(10, 35, 110, 20);
         studentSurnameField.setBounds(115,35,120,20);

         averageGradeLabel.setBounds(10, 60, 110, 20);
         averageGradeField.setBounds(115,60,120,20);

         addStudentBtn.setBounds(10, 135, 220, 20);
         saveStudentBtn.setBounds(10, 160, 220, 20);
         removeStudentBtn.setBounds(10, 185, 220, 20);

         studentsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if (studentsList.getSelectedValue() == null)
                {
                    return;
                }

                selectedItem = studentsList.getSelectedIndex();

                Student student = repo.Get(selectedItem);

                studentNameField.setText(student.name);
                studentSurnameField.setText(student.name);
                averageGradeField.setText(""+student.AverageGrade());
                hasPassedCheckBox.setSelected(student.HasPassed());
                isBudgetBox.setSelected(allowance.CanBeGiven(student));
            }
         });

         addStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name = studentNameField.getText().trim();
                String surname = studentSurnameField.getText().trim();
                String gradeStr = averageGradeField.getText().trim();
                boolean hasPassed = hasPassedCheckBox.isSelected();
                boolean isBudget = isBudgetBox.isSelected();

                if (name == "" || surname == "" || gradeStr == "")
                {
                    JOptionPane.showMessageDialog(null, "You havent entered all the values!");
                    return;
                }

                if (!IsNumber(gradeStr) )
                {
                    JOptionPane.showMessageDialog(null, "You havent entered a number!");
                    return;
                }

                Student student;

                if (isBudget == true)
                {
                    student = new StudentBudget(name, surname, hasPassed, Double.parseDouble(gradeStr));
                }
                else
                {
                    student = new Student(name, surname, hasPassed, Double.parseDouble(gradeStr));
                }

               repo.Create(student);
               ShowStudents(repo.Get());
            }
         });

         saveStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (selectedItem == -1)
                {
                    JOptionPane.showMessageDialog(null, "You havent choosen an item!");
                    return;
                }

                String name = studentNameField.getText().trim();
                String surname = studentSurnameField.getText().trim();
                String gradeStr = averageGradeField.getText().trim();
                boolean hasPassed = hasPassedCheckBox.isSelected();
                boolean isBudget = isBudgetBox.isSelected();

                if (name == "" || surname == "" || gradeStr == "")
                {
                    JOptionPane.showMessageDialog(null, "You havent entered all the values!");
                    return;
                }

                if (!IsNumber(gradeStr) )
                {
                    JOptionPane.showMessageDialog(null, "You havent entered a number!");
                    return;
                }

                Student student;

                if (isBudget == true)
                {
                    student = new StudentBudget(name, surname, hasPassed, Double.parseDouble(gradeStr));
                }
                else
                {
                    student = new Student(name, surname, hasPassed, Double.parseDouble(gradeStr));
                }

               repo.Update(student, selectedItem);
               ShowStudents(repo.Get());

               selectedItem = -1;
            }
         });

          removeStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (selectedItem == -1)
                {
                    JOptionPane.showMessageDialog(null, "You havent choosen an item!");
                    return;
                }

               
               repo.Delete(selectedItem);
               ShowStudents(repo.Get());

               selectedItem = -1;
            }
         });

         frame.add(hasPassedCheckBox);
         frame.add(hasPassedLabel);

         frame.add(isBudgetBox);
         frame.add(isBudgetLabel);

         frame.add(addStudentBtn);
         frame.add(saveStudentBtn);
         frame.add(removeStudentBtn);

         frame.add(studentsList);
        
         frame.add(studentNameField);
         frame.add(studentNameLabel);

         frame.add(studentSurnameField);
         frame.add(studentSurnameLabel);

         frame.add(averageGradeLabel);
         frame.add(averageGradeField);

         frame.setLayout(null);
         frame.setSize(900, 500);

         frame.setVisible(true);
         
    }

  public static void ShowStudents(ArrayList<Student> students)
  {
    DefaultListModel studs = new DefaultListModel();

   
        for (Student student: students)
        {
            String str = "Name: " + student.FullName() + ", Has Passed Exams: " + student.HasPassed() + ", Average Grade" + student.AverageGrade();
            
            if (allowance.CanBeGiven(student))
            {
                str += ", Scolar: " + allowance.GiveAllowance(student);
            }
            
            studs.addElement(str);
        }

        studentsList.setModel(studs);
    }



    public static boolean IsNumber(String str)
    {
        try
        {
            Double.parseDouble(str);

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}