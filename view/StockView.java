package view;

import listeners.*;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class StockView extends JPanel implements Observer {
    private static JFrame frame;
    private JTabbedPane tabbedPane;
    private int i;
    private FolioTracker folioTracker;


    private Object [] columns = new Object[] {
            "Ticker Symbol", "Stock Name", "Number of Shares", "Price per Share", "Value of Holding"
    };

    private Object [][] rowDataTest = new Object[][] {
            {  "1" ,"2" , "3" , "4", "5" },
            {  "5", "6", "7", "8", "9"   }


    };





    public StockView(FolioTracker folioTracker){
        this.folioTracker = folioTracker;

        folioTracker.addObserver(this);
       // controller = new listeners.StockController(s, this);

        i=0;
       // stockModel = m;
      //  stockModel.addObserver(this);
    }

    public void createAndShowGUI() throws InterruptedException {
        frame = new JFrame("Folio Tracker");
        frame.setPreferredSize(new Dimension(620, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //MENU
        JMenuBar menuB = new JMenuBar();
        JMenu menu = new JMenu("FOLIO");
        JMenuItem create = new JMenuItem("Create");
        JMenuItem exit = new JMenuItem("Exit");


        create.addActionListener(new createFolio(folioTracker , this));
        exit.addActionListener(new exitListener());

        menu.add(create);
        menu.add(exit);
        menuB.add(menu);
        frame.setJMenuBar(menuB);


        tabbedPane = new JTabbedPane();








        JButton close = new JButton("Close folio");
        close.addActionListener(new closeTab(this));

        JPanel bottomButtons = new JPanel();
        JButton refresh = new JButton("Refresh all values");
        refresh.addActionListener(new refreshStock(folioTracker));

        bottomButtons.add(refresh);
        bottomButtons.add(Box.createHorizontalGlue());

        bottomButtons.add(close);



        frame.add(bottomButtons,BorderLayout.SOUTH  );


        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);



       frame.add(tabbedPane);



        frame.pack();
        frame.setVisible(true);


    }


    public JTabbedPane getTabbedPane(){

        return tabbedPane;
    }
    public void update(Observable o, Object arg) {
        if (o instanceof Folio){
            Folio updatedFolio = (Folio) o;

            rebuildTable(updatedFolio);
        }

       else if (o instanceof FolioTracker){
            Folio newFolio = folioTracker.getAllFolio().get(folioTracker.getAllFolio().size()-1);
            newFolio.addObserver(this);
            rebuildTable(newFolio);
        }
        else if (o instanceof Stock){

            rebuildTable();



        }


    }

       public void rebuildTable(Folio updatedFolio){
        int index=-1;


        for (int i = 0 ; i <tabbedPane.getTabCount();i++) {

            if (tabbedPane.getTitleAt(i).equals(updatedFolio.getFolioName())){
                index = i;
                break;
            }

        }
            if (index>=0) {

                // remove tab thats changed
                tabbedPane.removeTabAt(index);
                //build that folio again and add it to that same index

            }

            List<Stock> stocks = updatedFolio.getStocks();



            JTable table = new JTable();
            table.setRowSelectionAllowed(true);


            DefaultTableModel model = new DefaultTableModel(null, columns) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }

            };

            table.setModel(model);

            for (Stock s : stocks) {

                Object row[] = new Object[]{s.getTickerSymbol(), s.getStockName(), s.getNumberOfShares(), s.getPricePerShare(), s.getValueOfHolding()};
                model.addRow(row);

                s.addObserver(this);


            }
            JPanel panel = new JPanel(new GridLayout());
            JScrollPane pane = new JScrollPane(table);
            table.addMouseListener(new tableClicked(this, folioTracker));


            JPanel addPanel = new JPanel();
            JLabel tickerSymbol = new JLabel("Ticker Symbol");
            JLabel numberOfShares = new JLabel("Number of shares ");


            JTextField tickerInput = new JTextField(10);
            JTextField numberOfSharesInput = new JTextField(10);


            JPanel rightPanel = new JPanel();
            JLabel totalValue = new JLabel("Total value for '" + updatedFolio.getFolioName() + "' = " + updatedFolio.getTotalHoldings());
            JButton addButton = new JButton("Add");
            addButton.addActionListener(new addStock(this, folioTracker));
            addPanel.setLayout(new GridLayout(3, 2));
            addPanel.add(tickerSymbol);
            addPanel.add(tickerInput);

            addPanel.add(numberOfShares);
            addPanel.add(numberOfSharesInput);



            addPanel.add(totalValue);
            addPanel.add(addButton);

            rightPanel.add(addPanel);


            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, rightPanel);
            splitPane.setLeftComponent(pane);
            splitPane.setRightComponent(rightPanel);
            splitPane.setDividerLocation(850);
          if(index>=0) {
              tabbedPane.insertTab(updatedFolio.getFolioName(), null, splitPane, null, index);

              tabbedPane.setSelectedIndex(index);

          }
          else{
              tabbedPane.addTab(updatedFolio.getFolioName(),splitPane);
          }


    }

    public String getTickerValue(){

        JSplitPane splitPane  = (JSplitPane) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());

        JPanel panel = (JPanel) splitPane.getRightComponent();
        JPanel panel1 = (JPanel) panel.getComponent(0);



        JTextField ticker = (JTextField) panel1.getComponent(1);


        return ticker.getText();

    }

    public void resetValues(){
        JSplitPane splitPane  = (JSplitPane) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());

        JPanel panel = (JPanel) splitPane.getRightComponent();
        JPanel panel1 = (JPanel) panel.getComponent(0);



        JTextField numberOfShares = (JTextField) panel1.getComponent(3);


        JTextField ticker = (JTextField) panel1.getComponent(1);

        numberOfShares.setText("");
        ticker.setText("");




    }
    public String getNumberOfShares(){

        JSplitPane splitPane  = (JSplitPane) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());

        JPanel panel = (JPanel) splitPane.getRightComponent();
        JPanel panel1 = (JPanel) panel.getComponent(0);



        JTextField numberOfShares = (JTextField) panel1.getComponent(3);



        return numberOfShares.getText();

    }


    public void buildTable(){


        List<Folio> allFolios = folioTracker.getAllFolio();




        for (Folio f : allFolios) {
            f.addObserver(this);
            JTable table = new JTable();
            table.setRowSelectionAllowed(true);

            DefaultTableModel model = new DefaultTableModel(null ,columns){

                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }

            };

            table.setModel(model);
            for (Stock s : f.getStocks()) {

                Object row[] = new Object[]{s.getTickerSymbol(), s.getStockName(), s.getNumberOfShares(), s.getPricePerShare(), s.getValueOfHolding()};
                model.addRow(row);
                s.addObserver(this);


            }


            JPanel panel = new JPanel(new GridLayout());
            JScrollPane pane = new JScrollPane(table);
            table.addMouseListener(new tableClicked(this, folioTracker));


            JPanel addPanel = new JPanel();
            JLabel tickerSymbol = new JLabel("Ticker Symbol");
            JLabel numberOfShares = new JLabel("Number of shares ");
      //      JLabel stockName = new JLabel("Stock Name ");


            JTextField tickerInput = new JTextField(10);
            JTextField numberOfSharesInput = new JTextField(10);
        //    JTextField stockNameInput = new JTextField(10);

            JPanel rightPanel = new JPanel();
            JLabel totalValue = new JLabel("Total value for "+f.getFolioName()+ " = " + f.getTotalHoldings());
            JButton addButton = new JButton("Add");
            addButton.addActionListener(new addStock(this, folioTracker));
            addPanel.setLayout(new GridLayout(3,2));
            addPanel.add(tickerSymbol);
            addPanel.add(tickerInput);


            addPanel.add(numberOfShares);
            addPanel.add(numberOfSharesInput);


            addPanel.add(totalValue);
            addPanel.add(addButton);

            rightPanel.add(addPanel);




            JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, panel,rightPanel );
            splitPane.setLeftComponent(pane);
            splitPane.setRightComponent(rightPanel);
            splitPane.setDividerLocation(750);
            tabbedPane.addTab(f.getFolioName(),splitPane);
        }


    }


    public void rebuildTable(){


        tabbedPane.removeAll();
        buildTable();
    }


    }