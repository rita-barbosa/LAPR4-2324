grammar RequirementsTemplate;

//Parser rules
start: template;

//Costitution of the template
template: requirement+;

//Costitution of a requirement
requirement : REQUIREMENT_NUMBER NEWLINE question+;

//Types of questions
question: single_choice_question
        | multiple_choice_question
        | integer_question
        | true_false_question
        | numeric_scale_question
        | decimal_question
        | short_answer_question
        | date_question
        | time_question
        ;

//Questions Format
single_choice_question: SINGLE_CHOICE WHITESPACE DESCRIPTION_TEXT NEWLINE option+;
multiple_choice_question: MULTIPLE_CHOICE WHITESPACE DESCRIPTION_TEXT NEWLINE option+;
integer_question: INTEGER WHITESPACE DESCRIPTION_TEXT NEWLINE*;
true_false_question: TRUE_FALSE WHITESPACE DESCRIPTION_TEXT NEWLINE*;
numeric_scale_question: NUMERIC_SCALE WHITESPACE DESCRIPTION_TEXT WHITESPACE SCALE_FORMAT NEWLINE*;
decimal_question: DECIMAL WHITESPACE DESCRIPTION_TEXT NEWLINE*;
short_answer_question: SHORT_ANSWER WHITESPACE DESCRIPTION_TEXT NEWLINE*;
date_question: DATE WHITESPACE DESCRIPTION_TEXT WHITESPACE DATE_FORMAT NEWLINE*;
time_question: TIME WHITESPACE DESCRIPTION_TEXT WHITESPACE TIME_FORMAT NEWLINE*;

// Option format
option : OPTION WHITESPACE DESCRIPTION_TEXT NEWLINE+;

//Formatters
OPTION : NUMBER')';
REQUIREMENT_NUMBER : 'REQUIREMENT #'NUMBER;
SCALE_FORMAT : '['NUMBER'-'NUMBER']';
DATE_FORMAT : '[DD/MM/YYYY]';
TIME_FORMAT : '[HH:MM]';
TRUE_FALSE_FORMAT : 'Write "True" or "False".';

//Lexer rules
//Types of questions identifiers
TRUE_FALSE : '[True/False]';
SINGLE_CHOICE : '[Single Choice]';
MULTIPLE_CHOICE : '[Multiple Choice]';
INTEGER : '[Whole Number]';
NUMERIC_SCALE : '[Numeric Scale]';
DECIMAL : '[Decimal Number]';
SHORT_ANSWER : '[Short Answer]';
DATE : '[Date]';
TIME : '[Time]';

WHITESPACE: ' ';
DESCRIPTION_TEXT: '"' (~["\r\n])* '"';
NUMBER: [0-9]+;
NEWLINE: '\r'?'\n';