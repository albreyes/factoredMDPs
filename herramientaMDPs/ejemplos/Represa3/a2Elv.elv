// Bayesian Network
//   Elvira format 

bnet  "" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node x(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (S0 S1 S2 S3 S4);
}

node y(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (S0 S1 S2 S3 S4);
}

node x_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (S0 S1 S2 S3 S4);
}

node y_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (S0 S1 S2 S3 S4);
}

// Links of the associated graph:

link x x_prime;

link y y_prime;

//Network Relationships: 

relation x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.1994910941475827 0.19389312977099238 0.19440203562340966 0.2223918575063613 0.18982188295165395 );
}

relation y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.29363867684478373 0.2193384223918575 0.2193384223918575 0.17201017811704836 0.09567430025445292 );
}

relation x_prime x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 );
}

relation y_prime y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.7677492980935875 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.23222070190641247 0.7563655104408352 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.24360448955916472 0.7099617981438515 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.2900082018561485 0.7751329289940829 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.22483707100591718 0.99996 );
}

}
