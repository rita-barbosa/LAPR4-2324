grammar Requirements_Front_End_Dev;

start: (group NEWLINE)+ group NEWLINE?;

group : requirement_header NEWLINE s_choice_q START_ANSWER s_choice_a
      | requirement_header NEWLINE m_choice_q START_ANSWER m_choice_a
      | requirement_header NEWLINE whole_n_q START_ANSWER whole_n_a
      | requirement_header NEWLINE true_false_q START_ANSWER true_false_a
      | requirement_header NEWLINE scale_q START_ANSWER scale_a
      | requirement_header NEWLINE decimal_q START_ANSWER decimal_a
      | requirement_header NEWLINE short_q START_ANSWER short_a
      | requirement_header NEWLINE date_q START_ANSWER date_a
      | requirement_header NEWLINE time_q START_ANSWER time_a
      ;

requirement_header : 'REQUIREMENT #'intNumber;

// Questions for Front End Developer
s_choice_q : '[Single Choice] "Do you prefer using React or Angular for front-end development?" Answer Model: "[Choice]"'NEWLINE'1) "React"'NEWLINE'2) "Angular"'NEWLINE;
m_choice_q : '[Multiple Choice] "Which of the following are front-end technologies?" Answer Model: "[CHOICE] and [CHOICE]"'NEWLINE'1) "HTML"'NEWLINE'2) "Python"'NEWLINE'3) "CSS"'NEWLINE;
whole_n_q : '[Whole Number] "How many years have you been working with JavaScript?"'NEWLINE;
true_false_q : '[True/False] "Are you familiar with responsive web design?"'NEWLINE;
scale_q : '[Numeric Scale] "Rate your proficiency in CSS with the given scale." Answer Model: [1-5]'NEWLINE;
decimal_q : '[Decimal Number] "What is the pixel width of a standard Bootstrap container?"'NEWLINE;
short_q : '[Short Answer] "Name a popular CSS preprocessor."'NEWLINE;
date_q : '[Date] "When did you start learning web development?" [DD/MM/YYYY]'NEWLINE;
time_q : '[Time] "At what time do you usually start coding?" [HH:MM]'NEWLINE;

//Answers for the questions
s_choice_a : ACTUAL_ANSWER NEWLINE;
m_choice_a :  m_choice NEWLINE;
whole_n_a :  intNumber NEWLINE;
scale_a :  intNumber NEWLINE;
true_false_a : BOOLEAN NEWLINE;
decimal_a : decimal NEWLINE;
short_a : short_answer NEWLINE;
date_a : date NEWLINE;
time_a : time NEWLINE;

decimal : NUM+ '.' NUM+;
short_answer : ACTUAL_ANSWER+;
date : NUM NUM'/'NUM NUM'/'NUM NUM NUM NUM;
time : NUM NUM ':' NUM NUM;
m_choice : ACTUAL_ANSWER ' and ' ACTUAL_ANSWER;
intNumber : NUM+ ;

BOOLEAN : ('True'|'False');
START_ANSWER : 'R:. ';
ACTUAL_ANSWER : [a-zA-Z]+;
NEWLINE : '\r'? '\n';
NUM : [0-9];
WS : [ \t\r]+ -> skip ;