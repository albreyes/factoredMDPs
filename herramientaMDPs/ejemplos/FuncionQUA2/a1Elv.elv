// Bayesian Network
//   Elvira format 

bnet  "" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node Q(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 25;
states = (q0 q1 q2 q3 q4 q5 q6 q7 q8 q9 q10 q11 q12 q13 q14 q15 q16 q17 q18 q19 q20 q21 q22 q23 q24);
}

node Q_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 25;
states = (q0 q1 q2 q3 q4 q5 q6 q7 q8 q9 q10 q11 q12 q13 q14 q15 q16 q17 q18 q19 q20 q21 q22 q23 q24);
}

// Links of the associated graph:

link Q Q_prime;

//Network Relationships: 

relation Q { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (1.0E-5 0.01705780421953026 1.0E-5 1.0E-5 1.0E-5 0.00852794972881275 0.0055174128497359805 0.03662629393352926 4.998513846080329E-4 0.004012144410197596 0.14550737772680572 0.5163051699997611 0.048668441449836335 0.0015033636776336224 0.006019168996248775 0.006019168996248775 0.002005119824146417 0.0015033636776336224 0.016556048073017467 0.010534974314863929 0.0035103882636848016 0.007022681289274365 0.13095644947793467 0.02458414641722218 0.007022681289274365 );
}

relation Q_prime Q { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (1.0E-5 0.20576735294117646 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.7940026470588235 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.027533706896551716 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.29404431372549017 0.5453395454545454 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.05875019607843136 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.6469854901960783 1.0E-5 0.150611598173516 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.45443045454545455 0.09581707762557079 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.7533513242009133 1.0E-5 1.0E-5 0.062016465517241375 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99976 0.749885 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.249885 0.0033957758620689646 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9068440517241378 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.32647811224489787 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99976 0.3091633505154639 0.99976 0.24992666666666666 0.99976 0.749885 0.99976 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.6906066494845361 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.2856617857142857 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.24992666666666666 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.4999266666666667 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.09512309523809521 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.249885 1.0E-5 0.24237174242424242 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.30297780303030303 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.09085659090909093 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.3635838636363637 0.9046469047619048 0.8570278571428571 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99976 1.0E-5 0.16321280612244893 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.14274214285714285 1.0E-5 1.0E-5 0.2244372959183673 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99976 1.0E-5 0.99976 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 );
}

}