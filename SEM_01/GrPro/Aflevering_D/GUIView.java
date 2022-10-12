import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;

public class GUIView {
    protected MockDB db;
    protected CustomerTracker cTracker;
    private JFrame frame;
    private JLabel output;
    
    private static final DecimalFormat df = new DecimalFormat("0.000");

    public GUIView() {
        db = new MockDB();
        cTracker = new CustomerTracker(db, 47);

        frame = new JFrame("Customer Tracker");
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1, 2));

        // Create right container first because it contains input/output
        // to be used in left container
        Container rightContainer = new Container();
        rightContainer.setLayout(new BorderLayout());

        output = new JLabel("Click any button!");

        JTextField input = new JTextField("Input week number");

        // Format input and ouput fields
        rightContainer.add(output, BorderLayout.PAGE_START);
        rightContainer.add(input, BorderLayout.PAGE_END);

        // Create left pane and add three buttons
        Container leftContainer = new Container();
        leftContainer.setLayout(new GridLayout(3, 1));

        JButton todayButton = new JButton("Today");
        todayButton.addActionListener((e) -> output.setText(Integer.toString(cTracker.today())));

        JButton avgButton = new JButton("Average this week");
        avgButton.addActionListener((e) -> output.setText(Double.toString(cTracker.avgThisWeek())));

        JButton compareButton = new JButton("Compare");
        compareButton.addActionListener((e) -> compare(input.getText()));

        // Add buttons to left pane
        leftContainer.add(todayButton);
        leftContainer.add(avgButton);
        leftContainer.add(compareButton);

        // Add left and right pane
        contentPane.add(leftContainer);
        contentPane.add(rightContainer);

        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,225);
    }

    private void today() {
        int out = cTracker.today();
        output.setText(Integer.toString(out));
    }

    private void avg() {
        double out = cTracker.avgThisWeek();
        output.setText(Double.toString(out));
    }

    private void compare(String week) {
        try {
            double diff = cTracker.comparedToWeek(Integer.parseInt(week));
            String out = df.format(diff);
            output.setText(out);
        } catch (Exception e) {
            output.setText("invalid input");
        }
    }
}
