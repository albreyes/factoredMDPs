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
values= table (0.20269705205937696 0.19935187121053732 0.20572862220363788 0.19966548191511604 0.1925569726113318 );
}

relation y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.23311729040351245 0.2528747647919716 0.25392013380723394 0.2560108718377587 0.004076939159523312 );
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
values= table (0.009858804185351271 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9869888490284006 0.011568364337880669 1.0E-5 1.0E-5 1.0E-5 0.003132346786248132 0.984697756648753 0.013579171126663922 1.0E-5 1.0E-5 1.0E-5 0.0037138790133664054 0.9827022670509127 0.011418238056349532 1.0E-5 1.0E-5 1.0E-5 0.0036985618224234943 0.9885517619436505 0.99996 );
}

}
