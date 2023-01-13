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
values= table (5.263711969681019E-4 0.2517107063901463 0.2504474155174229 0.25076323823560376 0.24655226865985894 );
}

relation y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.18317717654489946 0.191072744499421 0.21044320454784715 0.21107484998420886 0.20423202442362354 );
}

relation x_prime x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.99996 0.998312059807612 0.002515401429171921 1.0E-5 1.0E-5 1.0E-5 0.0016579401923881223 0.9957898865069356 0.0012527791771620486 1.0E-5 1.0E-5 1.0E-5 0.0016747120638923917 0.9932762888329135 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.005450931989924432 0.9948611742100768 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.0051088257899231425 );
}

relation y_prime y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 );
}

}
