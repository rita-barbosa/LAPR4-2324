grammar InterviewTemplate;

//Parser rules
start: template;

//Costitution of the template
template: interview+;

//Costitution of a interview
interview : INTERVIEW_NUMBER NEWLINE question+;

//Types of questions
question: true_false_question
        | short_answer_question
        | single_choice_question
        | multiple_choice_question
        | integer_question
        | decimal_question
        | date_question
        | time_question
        | numeric_scale_question
        ;


//Questions Format
true_false_question: QUESTION_TYPE SPACE TRUE_FALSE NEWLINE DESCRIPTION NEWLINE* solution+;
short_answer_question: QUESTION_TYPE SPACE SHORT_ANSWER NEWLINE DESCRIPTION NEWLINE* solution+;
single_choice_question: QUESTION_TYPE SPACE SINGLE_CHOICE NEWLINE DESCRIPTION NEWLINE option+ solution+;
multiple_choice_question: QUESTION_TYPE SPACE  MULTIPLE_CHOICE NEWLINE DESCRIPTION NEWLINE option+ solution+;
integer_question: QUESTION_TYPE SPACE INTEGER NEWLINE DESCRIPTION NEWLINE* solution+;
decimal_question: QUESTION_TYPE SPACE DECIMAL NEWLINE DESCRIPTION NEWLINE* solution+;
date_question: QUESTION_TYPE SPACE DATE NEWLINE DESCRIPTION SPACE DATE_FORMAT NEWLINE* solution+;
time_question: QUESTION_TYPE SPACE TIME NEWLINE DESCRIPTION SPACE TIME_FORMAT NEWLINE* solution+;
numeric_scale_question: QUESTION_TYPE SPACE NUMERIC_SCALE NEWLINE DESCRIPTION SPACE SCALE_FORMAT NEWLINE* solution+;


// Option format
option : ID_OPTION SPACE DESCRIPTION NEWLINE+;


//Solution format
solution : ID_SOLUTION SPACE SOLUTION_DESCRIPTION* NEWLINE+;


//Formatters
ID_OPTION : NUMBER')';
ID_SOLUTION : 'SOLUTION 'NUMBER ':';
INTERVIEW_NUMBER : 'QUESTION 'NUMBER;
QUESTION_TYPE : 'Type of Question:';
TRUE_FALSE_FORMAT : '"True" or "False"';
SCALE_FORMAT : '['NUMBER'-'NUMBER']';
DATE_FORMAT : 'DD/MM/YYYY';
TIME_FORMAT : 'HH:MM';


//Lexer rules
//Types of questions identifiers
TRUE_FALSE : 'TRUE/FALSE';
SHORT_ANSWER : 'SHORT ANSWER';
SINGLE_CHOICE : 'SINGLE CHOICE';
MULTIPLE_CHOICE : 'MULTIPLE CHOICE';
INTEGER : 'NUMBER';
DECIMAL : 'DECIMAL NUMBER';
DATE : 'DATE';
TIME : 'TIME';
NUMERIC_SCALE : 'NUMERIC SCALE';


//Solution
SOLUTION_DESCRIPTION : DESCRIPTION NEWLINE 'VALUE:' SPACE DECIMAL_NUMBER;


//Other identifiers
SPACE: ' ';
DESCRIPTION: '"' (~["\r\n])* '"';
NUMBER: [0-9]+;
DECIMAL_NUMBER: [1-9]*[0-9]+ '.' [0-9][0-9];
NEWLINE: '\r'?'\n';