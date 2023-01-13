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
values= table (0.2012966642267071 0.203492627836453 0.20882568231726445 0.19209453100491478 0.19429049461466066 );
}

relation y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.0021959636097458957 0.23214472445885181 0.2541043605563108 0.2532678029906933 0.2582871483843982 );
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
values= table (0.99996 0.9891741891891892 0.004520082304526748 1.0E-5 1.0E-5 1.0E-5 0.010795810810810812 0.9785941563786007 0.003296388659510047 1.0E-5 1.0E-5 1.0E-5 0.016865761316872428 0.9847167024497661 0.0048516329284750345 1.0E-5 1.0E-5 1.0E-5 0.01196690889072392 0.9789407017543861 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.016187665317139006 );
}

}
