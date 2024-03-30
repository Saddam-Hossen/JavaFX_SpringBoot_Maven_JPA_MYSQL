package org.example.spring.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.util.StringConverter;
import org.example.spring.controller.AddDataController;
import org.example.spring.model.EmployeeData;
import org.example.spring.model.EmployeeInsertedData;
import org.example.spring.model.UserGlobalSetting;
import org.example.spring.model.UserLocalSetting;
import org.example.spring.repository.RepositoryManager;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AtAGlanceDataView {
	RepositoryManager repositoryManager;
	int en=0; VBox dailyinfo;
	int datalength=0;
	int monthlength=0;
	String[] datek= {"","","","Date:"};
	String[] bark= {"SI.","Name","","Day:"};
	String[] truefalsek= {"","","","Is office day"};
	 int officedayc=0,presentdayc=0,avgtimec=0,totaltimec=0,leavedayc=0,absentdayc=0,holydayc=0,shorttimec=0,regulartimec=0,extratimec=0,intimec=0,latetimec=0,totallatetimec=0,okc=0,earlytimec=0,totalextratimec=0;
	AddDataController AddDataController;double outtimec=0;
	private ObservableList<String> names;
    private ListView<String> listView;
    private TextField searchField;
    private TextField dateTextField;
    int[] id=new int[500];
    Label totaltime,intotaltime, officeday,presentday,avgtime,leaveday,absentday,holyday,shorttime,regulartime,extratime,intime,latetime,totallatetime,ok,outtime,earlytime,totalextratime;
    Duration durationc=Duration.ZERO,totallatedurationc=Duration.ZERO,totalextradurationc=Duration.ZERO;Duration totaltimecc=Duration.ZERO;
    Duration intotaltimec=Duration.ZERO;
    
    TextField[] startreason;
    TextField[] endreason;
    ComboBox<String>[] statusbox;
    
    Spinner<Integer>[] starthour;
    Spinner<Integer>[] startminite;
    ComboBox<String>[] comboBoxstart;
    DatePicker[] datePicker;
    Spinner<Integer>[] endhour;
    Spinner<Integer>[] endminite;
    ComboBox<String>[] comboBoxend;
    TableView<AttendanceData> tableView;List<EmployeeData> employeeList;
    int length=0;  Label labelh3date,starttime; Label labelh2date; Label labelh1date,labelh1dateday,labelh2dateday,labelh3dateday;
    String selectedparson,selectedparsonglance;
    String selectedmonth,selectedyear,selectedmonthglance,selectedyearglance,selectedmonthday,selectedyearday;
    List<EmployeeInsertedData> dataList;
	List<UserGlobalSetting> globalSettingdata;List<UserLocalSetting> localSettingdata;
int top=4,bottom=4;
    BorderPane createTab(RepositoryManager repositoryManager1 , List<EmployeeData> employeeList1, List<EmployeeInsertedData> dataList1, List<UserGlobalSetting> globalSettingdata1, List<UserLocalSetting> localSettingdata1) {
		localSettingdata=localSettingdata1;
		globalSettingdata=globalSettingdata1;
		repositoryManager= repositoryManager1;
		 dataList=dataList1;employeeList=employeeList1;
		 length=employeeList.size();
		 datePicker=new  DatePicker[length];
		 startreason=new TextField[length];
		 endreason=new TextField[length];
		 statusbox = new ComboBox[length];
		 
		 starthour = new Spinner[length];
		 startminite = new Spinner[length];
		 comboBoxstart = new ComboBox[length];
		 endhour = new Spinner[length];
		 endminite = new Spinner[length];
		 comboBoxend = new ComboBox[length];
		Dimension screenSize = new Dimension(1300,650);
	        // Create labels for different panes
	        Label label1 = new Label("");
	        Label label2 = new Label("");

	        // Create nested StackPane for the left section
	        StackPane leftStackPane = new StackPane();
	        leftStackPane.setPrefWidth(0.1 * screenSize.getWidth()-70); // 30% of the total width
	        leftStackPane.getChildren().add(label2);

	        // Create nested StackPane for the center section
	        StackPane centerStackPane = new StackPane();
	        centerStackPane.setPrefWidth(0.9 *screenSize.getWidth()); // 40% of the total width
	        //centerStackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	       // centerStackPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");
	       
	       
	        VBox verticaldata=new VBox();

		HBox root11 = new HBox();
		DatePicker datePicker11 = new DatePicker();
		datePicker11.setMaxWidth(150);
		LocalDate currentDate11 = LocalDate.now();
		LocalDate initialDate11 = LocalDate.of(currentDate11.getYear(),currentDate11.getMonth(), 1);
		selectedmonthglance=Integer.toString(currentDate11.getMonthValue());
		selectedyearglance=Integer.toString(currentDate11.getYear());
		datePicker11.setValue(initialDate11);
		StringConverter<LocalDate> converter11 = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM,yyyy");

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					try {
						TemporalAccessor temporalAccessor = dateFormatter.parse(string);
						int year = temporalAccessor.get(ChronoField.YEAR);
						int month = temporalAccessor.get(ChronoField.MONTH_OF_YEAR);
						return LocalDate.of(year, month, 1); // Assuming day is always the 1st
					} catch (DateTimeParseException e) {
						// Handle parsing exception, e.g., return null or throw an error
						e.printStackTrace(); // You may want to log the exception
						return null;
					}
				} else {
					return null;
				}
			}
		};
		datePicker11.setConverter(converter11);

		// Add a listener to capture the selected month and year
		datePicker11.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				if (newValue != null) {
					int selectedYear = newValue.getYear();
					int selectedMonthValue = newValue.getMonthValue();
					String selectedMonth = newValue.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());

					selectedmonthglance=Integer.toString(selectedMonthValue);
					selectedyearglance=Integer.toString(selectedYear);
					setglanceclear();
					setglance();

					labelh2date.setText("Month: "+selectedMonth);
					labelh3date.setText("Month: "+selectedYear);
				}
			}
		});

		root11.setPadding(new Insets(5,0,10,0));
		root11.getChildren().addAll( datePicker11);
		verticaldata.getChildren().add(root11);
		   
		   HBox emplyeebox11 = new HBox(); // HBox is a horizontal box
		 
	        ComboBox<String> comboBox11 = new ComboBox<>();
	        comboBox11.setMinWidth(150);
	        emplyeebox11.setPadding(new Insets(10,0,10,0));

	        // Add items to the ComboBox
	        ObservableList<String> items11 = FXCollections.observableArrayList(
	        );
	        
	        employeeList.forEach(e->{
	        	
	        	items11.add(e.getName());
	        });
	        
	        comboBox11.setItems(items11);
	        
	        
			if(employeeList.size()>0)
			{
				// Set a default value (optional)
			    comboBox11.setValue(employeeList.get(0).getName());
			    selectedparsonglance=employeeList.get(0).getName();
			
			}
				        
	        // Handle selection change
	        comboBox11.setOnAction(event -> {
	            selectedparsonglance = comboBox11.getValue();
	            setglanceclear();
	            setglance();
	           
	           // settabledata();//  have to work
	        });
	        emplyeebox11.getChildren().add(comboBox11);
	        verticaldata.getChildren().add(emplyeebox11);
		   
		   HBox glrow1 = new HBox(); // HBox is a horizontal box
		   glrow1.setMaxWidth(1130);
		   glrow1.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   GridPane glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   HBox col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color:#CCCCCC; -fx-text-fill: white;");
		  
		   col1.getChildren().add(new Label("Office Day"));
		   glrow1colum1.add(col1,0,0);
		   
		   HBox col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   officeday=new Label();
		   col2.getChildren().add(officeday);
		   
		   glrow1colum1.add(col2,0,1);
		   
		   GridPane glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #B6D7A8; -fx-text-fill: white;");
		   col1.getChildren().add(new Label("Total Present"));
		   glrow1colum2.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   presentday=new Label();
		   col2.getChildren().add(presentday);
		   glrow1colum2.add(col2,0,1);
		   
		   GridPane glrow1colum3 = new GridPane();
		   //glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   HBox   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #4472C4; -fx-text-fill: white;");
		   Label totalTimeLabel = new Label("Avg Time");
		   totalTimeLabel.setStyle("-fx-text-fill: white;"); // Set text color for the label
		   col31.getChildren().add(totalTimeLabel);
		   glrow1colum3.add(col31,0,0);
		   
		   HBox  col32=new HBox();
		   col32.setPadding(new Insets(top,9,bottom,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   avgtime=new Label();
		   col32.getChildren().add(avgtime);
		  
		   glrow1colum3.add(col32,0,1);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow1.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		   verticaldata.getChildren().add(glrow1);
		   
		   
		   HBox glrow2 = new HBox(); // HBox is a horizontal box
		   glrow2.setMaxWidth(1130);
		   glrow2.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #FFE5A0; -fx-text-fill: white;");
		  
		   col1.getChildren().add(new Label("Leave"));
		   glrow1colum1.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   leaveday=new Label();
		   col2.getChildren().add(leaveday);
		   glrow1colum1.add(col2,0,1);
		   
		   glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #B10202; -fx-text-fill: white;");
		   totalTimeLabel = new Label("Absent");
		   totalTimeLabel.setStyle("-fx-text-fill: white;"); // Set text color for the label
		   col1.getChildren().add(totalTimeLabel);
		   glrow1colum2.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   absentday=new Label();
		   col2.getChildren().add(absentday);
		   glrow1colum2.add(col2,0,1);
		   
		   glrow1colum3 = new GridPane();
		   //glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #3D3D3D; -fx-text-fill: white;");
		   totalTimeLabel = new Label("Holiday");
		   totalTimeLabel.setStyle("-fx-text-fill: white;"); // Set text color for the label
		   col31.getChildren().add(totalTimeLabel);
		   glrow1colum3.add(col31,0,0);
		   
		   col32=new HBox();
		   col32.setPadding(new Insets(top,9,bottom,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   holyday=new Label();
		   col32.getChildren().add(holyday);
		   glrow1colum3.add(col32,0,1);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow2.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		   verticaldata.getChildren().add(glrow2);
		   
		   HBox glrow3 = new HBox(); // HBox is a horizontal box
		   glrow3.setMaxWidth(1130);
		   glrow3.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #F4CCCC; -fx-text-fill: white;");
		   col1.getChildren().add(new Label("Short Time"));
		   glrow1colum1.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   shorttime=new Label();
		   col2.getChildren().add(shorttime);
		   glrow1colum1.add(col2,0,1);
		   
		   glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #B7E1CD; -fx-text-fill: white;");
		   col1.getChildren().add(new Label("Regular Time"));
		   glrow1colum2.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   regulartime=new Label();
		   col2.getChildren().add( regulartime);
		   glrow1colum2.add(col2,0,1);
		   
		   glrow1colum3 = new GridPane();
		   //glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #38761D; -fx-text-fill: white;");
		   totalTimeLabel = new Label("Extra Time");
		   totalTimeLabel.setStyle("-fx-text-fill: white;"); // Set text color for the label
		   col31.getChildren().add(totalTimeLabel);
		   glrow1colum3.add(col31,0,0);
		   
		   col32=new HBox();
		   col32.setPadding(new Insets(top,9,bottom,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   extratime=new Label();
		   col32.getChildren().add(extratime);
		   glrow1colum3.add(col32,0,1);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow3.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		   verticaldata.getChildren().add(glrow3);
		   
		   HBox glrow4 = new HBox(); // HBox is a horizontal box
		   glrow4.setMaxWidth(1130);
		   glrow4.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 1px ;-fx-background-color: #9FC5E8; -fx-text-fill: white;");
		   col1.getChildren().add(new Label(" "));
		   glrow1colum1.add(col1,0,0);
		   
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #70AD47; -fx-text-fill: white;");
		   col1.getChildren().add(new Label(" In Time"));
		   glrow1colum1.add(col1,0,1);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   intime=new Label();
		   col2.getChildren().add(intime);
		   glrow1colum1.add(col2,0,2);
		   
		   glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #9FC5E8; -fx-text-fill: white;");
		   col1.getChildren().add(new Label(" Entry"));
		   glrow1colum2.add(col1,0,0);
		   
		   
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #F4CCCC; -fx-text-fill: white;");
		   col1.getChildren().add(new Label("Late"));
		   glrow1colum2.add(col1,0,1);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   latetime=new Label();
		   col2.getChildren().add(latetime);
		   glrow1colum2.add(col2,0,2);
		   
		   glrow1colum3 = new GridPane();
		   //glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 0px ;-fx-background-color: #9FC5E8; -fx-text-fill: white;");
		   col31.getChildren().add(new Label(" "));
		   glrow1colum3.add(col31,0,0);
		   
		   
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #EA9999; -fx-text-fill: white;");
		   col31.getChildren().add(new Label(" Total Late"));
		   glrow1colum3.add(col31,0,1);
		   
		   col32=new HBox();
		   col32.setPadding(new Insets(top,9,bottom,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   totallatetime=new Label();
		   col32.getChildren().add(totallatetime);
		   glrow1colum3.add(col32,0,2);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow4.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		   verticaldata.getChildren().add(glrow4);
		   
		   
		   HBox glrow5 = new HBox(); // HBox is a horizontal box
		   glrow5.setMaxWidth(1130);
		   glrow5.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER_RIGHT);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 1px ;-fx-background-color: #D9D2E9; -fx-text-fill: white;");
		   col1.getChildren().add(new Label("Exit"));
		   glrow1colum1.add(col1,0,0);
		   
		   
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #70AD47; -fx-text-fill: white;");
		   col1.getChildren().add(new Label("Ok"));
		   glrow1colum1.add(col1,0,1);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   ok=new Label();
		   col2.getChildren().add(ok);
		   glrow1colum1.add(col2,0,2);
		   
		   glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #D9D2E9; -fx-text-fill: white;");
		   col1.getChildren().add(new Label(""));
		   glrow1colum2.add(col1,0,0);
		   
		   
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #F4CCCC; -fx-text-fill: white;");
		   col1.getChildren().add(new Label(" Early"));
		   glrow1colum2.add(col1,0,1);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   earlytime=new Label();
		   col2.getChildren().add(earlytime);
		   glrow1colum2.add(col2,0,2);
		   
		   glrow1colum3 = new GridPane();
		   //glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #D9EAD3; -fx-text-fill: white;");
		   col31.getChildren().add(new Label("Total Extra Time"));
		   glrow1colum3.add(col31,0,0);
		   
		   col32=new HBox();
		   col32.setPadding(new Insets(top*4,9,bottom*4+2,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   totalextratime=new Label();
		   col32.getChildren().add( totalextratime);
		   glrow1colum3.add(col32,0,1);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow5.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		   verticaldata.getChildren().add(glrow5);
		   
		   
		   HBox glrow6 = new HBox(); // HBox is a horizontal box
		   glrow6.setMaxWidth(1130);
		   glrow6.setStyle("-fx-padding:5px");
		  // VBox glrow1colum1 = new VBox(); // HBox is a horizontal box
		   glrow1colum1 = new GridPane();
		  // glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width:0px 0px 0px 0px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #F4CCCC; -fx-text-fill: white;");
		   col1.getChildren().add(new Label(" Office Out Time"));
		   glrow1colum1.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(400);
		   col2.setStyle("-fx-border-color: black; -fx-border-width:0px 1px 1px 1px;");
		   outtime=new Label();
		   col2.getChildren().add(outtime);
		   glrow1colum1.add(col2,0,1);
		   
		   glrow1colum2 = new GridPane();
		   //glrow1colum2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col1=new HBox();
		   col1.setPadding(new Insets(top,9,bottom,0));;
		   col1.setAlignment(Pos.CENTER);
		   col1.setMinWidth(400);
		   col1.setStyle("-fx-border-color: black; -fx-border-width: 1px 0px 0px 0px;-fx-background-color: #B7E1CD; -fx-text-fill: white;");
		   col1.getChildren().add(new Label(" Office In Time"));
		   glrow1colum2.add(col1,0,0);
		   
		   col2=new HBox();
		   col2.setPadding(new Insets(top,9,bottom,0));;
		   col2.setAlignment(Pos.CENTER);
		   col2.setMinWidth(300);
		   col2.setStyle("-fx-border-color: black; -fx-border-width: 0px 0px 1px 0px;");
		   intotaltime=new Label();
		   col2.getChildren().add(intotaltime);
		   
		   glrow1colum2.add(col2,0,1);
		   
		   glrow1colum3 = new GridPane();
		   //glrow1colum1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		   col31=new HBox();
		   col31.setPadding(new Insets(top,9,bottom,0));;
		   col31.setAlignment(Pos.CENTER);
		   col31.setMinWidth(302);
		   col31.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 0px 1px ;-fx-background-color: #38761D; -fx-text-fill: white;");
		   totalTimeLabel = new Label("Total Time");
		   totalTimeLabel.setStyle("-fx-text-fill: white;"); // Set text color for the label
		   col31.getChildren().add(totalTimeLabel);
		   glrow1colum3.add(col31,0,0);
		   
		   col32=new HBox();
		   col32.setPadding(new Insets(top,9,bottom,0));;
		   col32.setAlignment(Pos.CENTER);
		   col32.setMinWidth(302);
		   col32.setStyle("-fx-border-color: black; -fx-border-width: 0px 1px 1px 1px;");
		   totaltime=new Label();
		   col32.getChildren().add(totaltime);
		  
		   glrow1colum3.add(col32,0,1);
		  
		  
		   glrow1colum1.setPrefWidth(400);
	        glrow1colum2.setPrefWidth(400);
	        glrow1colum3.setPrefWidth(400);
		   glrow6.getChildren().addAll(glrow1colum1, glrow1colum2, glrow1colum3);
		   
		     verticaldata.getChildren().add(glrow6);

		   setglance();
		   


		   // give margin padding
		    ScrollPane scrollPane = new ScrollPane();
		    scrollPane.setContent(verticaldata);
		     // scrollPane.setFitToWidth(true); // Adjusts the width of the scroll pane to its content
		   // scrollPane.setFitToHeight(true); // Adjusts the height of the scroll pane to its content


		     centerStackPane.getChildren().add(verticaldata);
            
            
	        // Create nested StackPane for the right section
	        StackPane rightStackPane = new StackPane();
	        rightStackPane.setPrefWidth(0.0 * screenSize.getWidth()-70); // 30% of the total width
	        rightStackPane.getChildren().add(new Label(""));
	       

	        // Create an HBox to hold the left, center, and right sections
	        HBox hbox = new HBox();
	        hbox.getChildren().addAll(leftStackPane, centerStackPane, rightStackPane);


	        // Create a BorderPane and set the HBox in the center
	        BorderPane borderPane = new BorderPane();
	     // Set padding using CSS
	        borderPane.setStyle("-fx-padding: 10 10 50 10;");
		  //  VBox.setMargin(borderPane , new Insets(0, 0, 100, 0)); // 10 pixels of bottom margin
	        
	        borderPane.setTop(new Label(""));
	        borderPane.setBottom(new Label(""));
	        borderPane.setCenter(hbox);



	        // Create a new tab with the specified title and content
	        //Tab tab = new Tab(tabTitle);


	        return borderPane;
	    }

    

    public String officeday(String name, String month,String year)
    {
    	officedayc=0;
   	    dataList.forEach(e->{
   		 //office day
   		
	        	if(name.equals(e.getName())&&month.equals(e.getMonth())&&year.equals(e.getYear()) )
	        	{
	        		if(!"Holyday".equals(e.getStatus()))
	        		{
	        			officedayc++;
	        		}
	        	}
   	     });
   	    return (Integer.toString(officedayc));
    }
   public int datalength(String name,String month,String year)
   {
	   datalength=0;
	   for (int i = 0; i < dataList.size(); i++) {
       	if(name.equals(dataList.get(i).getName())&& month.equals(dataList.get(i).getMonth())&&dataList.get(i).getYear().equals(year))
       	{
       		
       		datalength++;
       		
       	}
           
       }
	   
	   return datalength;
   }
    
   public String[] getFirstTwoCharacters(String name,String month,String year) {
       String[] result = new String[monthlength];
          int k=0;
       for (int i = 0; i < dataList.size(); i++) {
    	   if(name.equals(dataList.get(i).getName())&& month.equals(dataList.get(i).getMonth())&&dataList.get(i).getYear().equals(year))
    	   {
    		   // Get the day of the week for each LocalDateTime
               DayOfWeek dayOfWeek = dataList.get(i).getEntryTime().getDayOfWeek();

               // Get the first two characters of the day name
               String firstTwoCharacters = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH).substring(0, 2);

               // Store the result in the array
               result[k] = firstTwoCharacters;
               k++;
    	   }
          
       }

       return result;
   }
   
   public String[] getstatus(String name,String month,String year) {
       String[] result = new String[monthlength];
          int k=0;
       for (int i = 0; i < dataList.size(); i++) {
    	   if(name.equals(dataList.get(i).getName())&& month.equals(dataList.get(i).getMonth())&&dataList.get(i).getYear().equals(year))
    	   {
               // Store the result in the array
               result[k] = dataList.get(i).getStatus();
               k++;
    	   }
          
       }

       return result;
   }
   public String[] getday(String name,String month,String year) {
       String[] result = new String[monthlength];
          int k=0;
       for (int i = 0; i < dataList.size(); i++) {
    	   if(name.equals(dataList.get(i).getName())&& month.equals(dataList.get(i).getMonth())&&dataList.get(i).getYear().equals(year))
    	   {
               // Store the result in the array
               result[k] = Integer.toString(dataList.get(i).getEntryTime().getDayOfMonth());
               k++;
    	   }
          
       }

       return result;
   }

    public void setglanceclear() {
    	// regulartime,extratime,intime,latetime,totallatetime,ok,earlytime,totalextratime;
    	 officeday.setText("");
    	 presentday.setText("");
    	 avgtime.setText("");
    	 leaveday.setText("");
    	 absentday.setText("");
    	 holyday.setText("");
    	 shorttime.setText("");
    	 regulartime.setText("");
    	 extratime.setText("");
    	 intime.setText("");
    	 latetime.setText("");
    	 totallatetime.setText("");
    	 ok.setText("");
    	 earlytime.setText("");
    	 totalextratime.setText("");
    
    }
    public void setglance()
    {
		System.out.println(selectedparsonglance);
    	 
    	 officedayc=0;presentdayc=0;avgtimec=0;leavedayc=0;absentdayc=0;holydayc=0;shorttimec=0;regulartimec=0;extratimec=0;intimec=0;latetimec=0;totallatetimec=0;okc=0;earlytimec=0;totalextratimec=0;
    	 durationc=Duration.ZERO;totallatedurationc=Duration.ZERO;totalextradurationc=Duration.ZERO;outtimec=0;totaltimecc=Duration.ZERO;
    	 intotaltimec=Duration.ZERO;outtimec=0;totaltimecc=Duration.ZERO;
    	
    	 if(dataList.size()>0)
    	 {
    		 dataList.forEach(e->{
        		 //office day
        		
    	        	if(selectedparsonglance.equals(e.getName())&&selectedmonthglance.equals(e.getMonth())&&selectedyearglance.equals(e.getYear()) )
    	        	{
    	        		if(!"Holyday".equals(e.getStatus()))
    	        		{
    	        			officedayc++;
    	        		}
    	        		
    	        		if("Present".equals(e.getStatus()))
    	        		{
    	        			durationc = durationc.plus(Duration.between(e.getEntryTime(), e.getExitTime()));
    	        			
    	        			presentdayc++;
    	        			
    	        			Duration durationBetweenEntryExit = Duration.between(e.getEntryTime(), e.getExitTime());

    	        			long hours = durationBetweenEntryExit.toHoursPart();
    	        	        long minutes = durationBetweenEntryExit.toMinutesPart();
							int settingHours= returnSettingTotalHour(e.getEmployeeId(),e.getName(),e.getEntryDate());
							System.out.println(settingHours);
    	        	     
    	        	        if (hours < settingHours ) {
    	        	           
    	        	            shorttimec++;
    	        	        } else if (hours > settingHours || (hours == settingHours && minutes > 0)) {
    	        	          
    	        	            extratimec++;
    	        	        } else {
    	        	        	regulartimec++;
    	        	          
    	        	        }

    	        			 LocalTime lateThreshold = LocalTime.of(returnSettingStartHour(e.getEmployeeId(),e.getName(),e.getEntryDate()), (returnGlobalSettingLateMinute(e.getEntryDate())+1));
    	        		 	    
    	     	            if ( e.getEntryTime().toLocalTime().isBefore(lateThreshold)) {
    	     	            	intimec++;
    	     	            }
    	     	           lateThreshold = LocalTime.of(returnSettingStartHour(e.getEmployeeId(),e.getName(),e.getEntryDate()), returnGlobalSettingLateMinute(e.getEntryDate()));
    	     	           // late count
    	     	            if ( e.getEntryTime().toLocalTime().isAfter(lateThreshold)) {
    	     	            	latetimec++;
								LocalTime lateThreshold1 =LocalTime.of(returnSettingStartHour(e.getEmployeeId(),e.getName(),e.getEntryDate()),returnSettingStartMinute(e.getEmployeeId(),e.getName(),e.getEntryDate()));
    	     	            	//late duration count
    	     	            	Duration duration = Duration.between(lateThreshold1, e.getEntryTime().toLocalTime());

    	   	            	    totallatedurationc=totallatedurationc.plus(duration);
    	     	            }

							String[] data=subtractHourMinute(returnSettingEndHour(e.getEmployeeId(),e.getName(),e.getEntryDate()),returnGlobalSettingEarlyMinute(e.getEntryDate()));
    	     	           LocalTime exitThreshold = LocalTime.of(Integer.parseInt(data[0]),Integer.parseInt(data[1]) );
    	     	           if ( e.getExitTime().toLocalTime().isBefore(exitThreshold)) {
    	     	            	earlytimec++;
    	     	            }

							exitThreshold = LocalTime.of(returnSettingEndHour(e.getEmployeeId(),e.getName(),e.getEntryDate()),returnSettingEndMinute(e.getEmployeeId(),e.getName(),e.getEntryDate()) );
    	     	           
    	     	           if ( e.getExitTime().toLocalTime().isAfter(exitThreshold)) {

    	     	        	  
    	     	        	 Duration duration = Duration.between(exitThreshold, e.getExitTime().toLocalTime());
    	   	           
    	     	        	 totalextradurationc=totalextradurationc.plus(duration);
    	     	        	  
    	     	            }
    	     	           
    	     	           outtimec=+outtimec+Double.parseDouble(e.getOuttime());
    	     	          
    	        		}
    	        		
    	        		if("Leave".equals(e.getStatus()))
    	        		{
    	        			
    	        			
    	        			leavedayc++;
    	        		}
    	        		
    	        		if("Absent".equals(e.getStatus()))
    	        		{
    	        			
    	        			
    	        			absentdayc++;
    	        		}
    	        		
    	        		if("Holyday".equals(e.getStatus()))
    	        		{
    	        			
    	        			
    	        			holydayc++;
    	        		}
    	        		
            		    
        	            
    	        		
    	        		
    	        	}
        	 });
        	 
        	 officeday.setText(Integer.toString(officedayc));
        	 presentday.setText(Integer.toString(presentdayc));
        	 intotaltimec=durationc;
        	 
        	 outtime.setText(Double.toString(outtimec));

             // Convert decimal hours to seconds
             long seconds = (long) (outtimec * 60 * 60);

             // Create a Duration object
             Duration outtimeduration = Duration.ofSeconds(seconds);
             
            
             totaltimecc= intotaltimec.plus(outtimeduration);
           
             intotaltime.setText(intotaltimec.toHours()+":"+ intotaltimec.toMinutesPart());
        	 totaltime.setText(totaltimecc.toHours()+":"+ totaltimecc.toMinutesPart());
        	 
        	 durationc=durationc.dividedBy(presentdayc);
        	
        	 avgtime.setText( durationc.toHoursPart()+":"+ durationc.toMinutesPart());
        	 
        	 leaveday.setText(Integer.toString(leavedayc));
        	 absentday.setText(Integer.toString(absentdayc));
        	 holyday.setText(Integer.toString(holydayc));
        	 shorttime.setText(Integer.toString(shorttimec));
        	 regulartime.setText(Integer.toString(regulartimec));
        	 extratime.setText(Integer.toString(extratimec));
        	 intime.setText(Integer.toString(intimec));
        	 latetime.setText(Integer.toString(latetimec));
        	 totallatetime.setText( totallatedurationc.toHoursPart()+":"+ totallatedurationc.toMinutesPart());
        	 ok.setText(Integer.toString(presentdayc-earlytimec));
        	 earlytime.setText(Integer.toString(earlytimec));
        	 totalextratime.setText( totalextradurationc.toHoursPart()+":"+ totalextradurationc.toMinutesPart()); 
    	 }
    	 
    	 
    	 
    	
    }
	public static String[] subtractHourMinute(int hour, int minutesToSubtract) {
		// Convert everything to minutes for easier calculation
		int totalMinutes = hour * 60 ;
		totalMinutes -= minutesToSubtract;

		// Handle negative result
		if (totalMinutes < 0) {
			totalMinutes += 24 * 60; // Assuming a 24-hour clock
		}

		// Calculate resulting hour and minute
		int resultingHour = totalMinutes / 60;
		int resultingMinute = totalMinutes % 60;

		// Format the result
		String[] result = new String[2];
		result[0] = String.valueOf(resultingHour);
		result[1] = String.valueOf(resultingMinute);
		return result;
	}
	 public static String checkTimeDifference(Duration duration) {
	       
	        long hours = duration.toHoursPart();
	        long minutes = duration.toMinutesPart();

	        String result;
	        if (hours < 8 ) {
	            result = "Short time";
	        } else if (hours > 8 || (hours == 8 && minutes > 0)) {
	            result = "Extra time";
	        } else {
	            result = "Required time";
	        }

	        return result;
	    }

	public int returnGlobalSettingLateMinute(String insertDataDate1)
	{
		int hour = 9; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = globalSettingdata.size() - 1; i >= 0; i--) {

			try {
				Date insertDataDate = dateFormat.parse(insertDataDate1);
				Date startDate = dateFormat.parse(globalSettingdata.get(i).getFormattedBirthDate());
				Date endDate = dateFormat.parse(globalSettingdata.get(i).getFormattedDeathDate());

				// Check if insertDataDate is between startDate and endDate (inclusive)
				if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
						(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
					hour = Integer.parseInt(globalSettingdata.get(i).getLateMinute());
					break;
				}
			} catch (java.text.ParseException e) {
				// Handle parsing exception appropriately, e.g., logging
				e.printStackTrace();
			}
		}



		return hour;
	}
	public int returnGlobalSettingEarlyMinute(String insertDataDate1)
	{
		int hour = 9; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = globalSettingdata.size() - 1; i >= 0; i--) {

			try {
				Date insertDataDate = dateFormat.parse(insertDataDate1);
				Date startDate = dateFormat.parse(globalSettingdata.get(i).getFormattedBirthDate());
				Date endDate = dateFormat.parse(globalSettingdata.get(i).getFormattedDeathDate());

				// Check if insertDataDate is between startDate and endDate (inclusive)
				if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
						(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
					hour = Integer.parseInt(globalSettingdata.get(i).getEarlyMinute());
					break;
				}
			} catch (java.text.ParseException e) {
				// Handle parsing exception appropriately, e.g., logging
				e.printStackTrace();
			}
		}



		return hour;
	}
	public int returnSettingStartMinute(String id, String name,String insertDataDate1)
	{
		int hour = 9; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = localSettingdata.size() - 1; i >= 0; i--) {
			if (id.equals(localSettingdata.get(i).getEmployeeId()) && name.equals(localSettingdata.get(i).getName())) {
				try {
					Date insertDataDate = dateFormat.parse(insertDataDate1);
					Date startDate = dateFormat.parse(localSettingdata.get(i).getFormattedBirthDate());
					Date endDate = dateFormat.parse(localSettingdata.get(i).getFormattedDeathDate());

					// Check if insertDataDate is between startDate and endDate (inclusive)
					if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
							(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
						hour = Integer.parseInt(localSettingdata.get(i).getStartMinute());
						break;
					}
				} catch (java.text.ParseException e) {
					// Handle parsing exception appropriately, e.g., logging
					e.printStackTrace();
				}
			}
		}


		return hour;
	}
	public int returnSettingEndHour(String id, String name,String insertDataDate1)
	{
		int hour = 9; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = localSettingdata.size() - 1; i >= 0; i--) {
			if (id.equals(localSettingdata.get(i).getEmployeeId()) && name.equals(localSettingdata.get(i).getName())) {
				try {
					Date insertDataDate = dateFormat.parse(insertDataDate1);
					Date startDate = dateFormat.parse(localSettingdata.get(i).getFormattedBirthDate());
					Date endDate = dateFormat.parse(localSettingdata.get(i).getFormattedDeathDate());

					// Check if insertDataDate is between startDate and endDate (inclusive)
					if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
							(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
						hour = Integer.parseInt(localSettingdata.get(i).getEndHours());
						break;
					}
				} catch (java.text.ParseException e) {
					// Handle parsing exception appropriately, e.g., logging
					e.printStackTrace();
				}
			}
		}


		return hour;
	}
	public int returnSettingEndMinute(String id, String name,String insertDataDate1)
	{
		int hour = 9; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = localSettingdata.size() - 1; i >= 0; i--) {
			if (id.equals(localSettingdata.get(i).getEmployeeId()) && name.equals(localSettingdata.get(i).getName())) {
				try {
					Date insertDataDate = dateFormat.parse(insertDataDate1);
					Date startDate = dateFormat.parse(localSettingdata.get(i).getFormattedBirthDate());
					Date endDate = dateFormat.parse(localSettingdata.get(i).getFormattedDeathDate());

					// Check if insertDataDate is between startDate and endDate (inclusive)
					if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
							(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
						hour = Integer.parseInt(localSettingdata.get(i).getEndMinute());
						break;
					}
				} catch (java.text.ParseException e) {
					// Handle parsing exception appropriately, e.g., logging
					e.printStackTrace();
				}
			}
		}


		return hour;
	}
	public int returnSettingStartHour(String id, String name,String insertDataDate1)
	{
		int hour = 9; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = localSettingdata.size() - 1; i >= 0; i--) {
			if (id.equals(localSettingdata.get(i).getEmployeeId()) && name.equals(localSettingdata.get(i).getName())) {
				try {
					Date insertDataDate = dateFormat.parse(insertDataDate1);
					Date startDate = dateFormat.parse(localSettingdata.get(i).getFormattedBirthDate());
					Date endDate = dateFormat.parse(localSettingdata.get(i).getFormattedDeathDate());

					// Check if insertDataDate is between startDate and endDate (inclusive)
					if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
							(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
						hour = Integer.parseInt(localSettingdata.get(i).getStartHours());
						break;
					}
				} catch (java.text.ParseException e) {
					// Handle parsing exception appropriately, e.g., logging
					e.printStackTrace();
				}
			}
		}


		return hour;
	}
	public int returnSettingTotalHour(String id, String name,String insertDataDate1)
	{
		int hour = 8; // Default hour value

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = localSettingdata.size() - 1; i >= 0; i--) {
			if (id.equals(localSettingdata.get(i).getEmployeeId()) && name.equals(localSettingdata.get(i).getName())) {
				try {
					Date insertDataDate = dateFormat.parse(insertDataDate1);
					Date startDate = dateFormat.parse(localSettingdata.get(i).getFormattedBirthDate());
					Date endDate = dateFormat.parse(localSettingdata.get(i).getFormattedDeathDate());

					// Check if insertDataDate is between startDate and endDate (inclusive)
					if ((insertDataDate.equals(startDate) || insertDataDate.equals(endDate)) ||
							(insertDataDate.after(startDate) && insertDataDate.before(endDate))) {
						hour = Integer.parseInt(localSettingdata.get(i).getTotalHours());
						break;
					}
				} catch (java.text.ParseException e) {
					// Handle parsing exception appropriately, e.g., logging
					e.printStackTrace();
				}
			}
		}


		return hour;
	}
	 public static Duration addMinutesToDuration(Duration originalDuration, long minutesToAdd) {
	        return originalDuration.plusMinutes(minutesToAdd);
	    }
	 private void updateDateTextField(LocalDate date) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        dateTextField.setText(date.format(formatter));
	    }

	    private void incrementDate() {
	        LocalDate currentDate = LocalDate.parse(dateTextField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        LocalDate nextDate = currentDate.plusDays(1);
	        updateDateTextField(nextDate);
	        
	        // Extract year and month separately
	        int year = nextDate.getYear();
	        Month month = nextDate.getMonth();

	        // Print year and month separately
	        System.out.println("Year: " + year);
	        System.out.println("Month: " + month);
	        
	        labelh1date.setText("Daily Attendance Sheet-"+year);
	        labelh2date.setText("Month : "+month);
	        labelh3date.setText("   Date: "+nextDate.toString());
	        for(int i=0;i<length;i++)
	        {
	        	 datePicker[i].setValue(nextDate); // Set initial date
	        }
	        
	    }
	    
	    public LocalDateTime createLocalDateTime(int year, int month, int dayOfMonth, int hour, int minute, String amPm) {
	        int adjustedHour = hour % 12;
	        if (amPm.equalsIgnoreCase("PM")) {
	            adjustedHour += 12;
	        }
	        return LocalDateTime.of(year, month, dayOfMonth, adjustedHour, minute);
	    }

	     private TableColumn<AttendanceData, String> createColumn(String columnName, String propertyName, double minWidth) {
	         TableColumn<AttendanceData, String> column = new TableColumn<>(columnName);
	         column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
	         column.setMinWidth(minWidth);
	         
	      // Set the cell factory to center-align the content
	         column.setCellFactory(col -> new TableCell<AttendanceData, String>() {
	             @Override
	             protected void updateItem(String item, boolean empty) {
	                 super.updateItem(item, empty);
	                 if (item == null || empty) {
	                     setText(null);
	                 } else {
	                     setText(item);
	                     setAlignment(Pos.CENTER);
	                 }
	             }
	         });
	        // column.setStyle("-fx-background-color: #a0a0a0; -fx-text-fill: white;");
	         return column;
	     }
	   

	     public class AttendanceData {

	         private final SimpleStringProperty date;
	         private final SimpleStringProperty entryTime;
	         private final SimpleStringProperty lateDuration;
	         private final SimpleStringProperty entryComment;
	         private final SimpleStringProperty exitTime;
	         private final SimpleStringProperty timeAfterExit;
	         private final SimpleStringProperty exitComment;
	        
	         private final SimpleStringProperty totalTimeInDay;
	         private final SimpleStringProperty dayComment;
	         private final SimpleStringProperty comment;

	         public AttendanceData(String date, String entryTime, String lateDuration, String entryComment,
	                               String exitTime, String timeAfterExit, String exitComment,
	                               String totalTimeInDay, String dayComment, String comment) {
	             this.date = new SimpleStringProperty(date);
	             this.entryTime = new SimpleStringProperty(entryTime);
	             this.lateDuration = new SimpleStringProperty(lateDuration);
	             this.entryComment = new SimpleStringProperty(entryComment);
	             this.exitTime = new SimpleStringProperty(exitTime);
	             this.timeAfterExit = new SimpleStringProperty(timeAfterExit);
	             this.exitComment = new SimpleStringProperty(exitComment);
	             
	             this.totalTimeInDay = new SimpleStringProperty(totalTimeInDay);
	             this.dayComment = new SimpleStringProperty(dayComment);
	             this.comment = new SimpleStringProperty(comment);
	         }

	         // Generate getters for all properties
	         public String getDate() {
	             return date.get();
	         }

	         public String getEntryTime() {
	             return entryTime.get();
	         }

	         public String getLateDuration() {
	             return lateDuration.get();
	         }

	         public String getEntryComment() {
	             return entryComment.get();
	         }

	         public String getExitTime() {
	             return exitTime.get();
	         }

	         public String getTimeAfterExit() {
	             return timeAfterExit.get();
	         }

	         public String getExitComment() {
	             return exitComment.get();
	         }

	         

	         public String getTotalTimeInDay() {
	             return totalTimeInDay.get();
	         }

	         public String getDayComment() {
	             return dayComment.get();
	         }

	         public String getComment() {
	             return comment.get();
	         }
	     }


	  public  class DataModel {
		   
		    // ... Repeat for columns 4 to 30
		    private String[] columns = new String[ monthlength+5]; // Assuming 31 columns, adjust as needed

		    public DataModel(ArrayList<String> rowdata ) {
		    	System.out.println( rowdata.size()+"  jjl  "+columns.length);
		    	
		        // Initialize columns with default values
		        for (int i = 0; i < columns.length; i++) {
		        	
		            columns[i] = rowdata.get(i);
		        }
		    }

		   

		    // ... Repeat for other columns

		    // Method to retrieve column property based on index
		    public StringProperty getColumnProperty(int index) {
		        if (index >= 0 && index < columns.length) {
		            return new SimpleStringProperty(columns[index]);
		        }
		        return null;
		    }
		}
}
