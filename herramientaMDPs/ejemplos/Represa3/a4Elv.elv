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

link x y;

link y y_prime;

//Network Relationships: 

relation x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.18860698898994735 0.19004308281474389 0.19722355193872668 0.22642412637625658 0.1977022498803255 );
}

relation y x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.36548223350253806 0.3400503778337531 0.21359223300970875 0.24735729386892177 0.2348668280871671 0.2131979695431472 0.19395465994962216 0.22330097087378642 0.15644820295983086 0.288135593220339 0.1446700507614213 0.17632241813602015 0.18203883495145634 0.2748414376321353 0.26634382566585957 0.1903553299492386 0.1889168765743073 0.19417475728155342 0.20718816067653276 0.07506053268765134 0.08629441624365482 0.10075566750629722 0.18689320388349517 0.11416490486257928 0.13559322033898305 );
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
values= table (0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 );
}

}
