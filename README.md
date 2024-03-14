im thinking a little about how remote partitioning would work with JobRunr. 

would be dope if there was a message handler to send the requests from the leader node to any of the background/worker nodes in our system. 

the batch job leader step would eventually launch the message handler which would in turn enqueue a request for us containing, basically, the contents of the `StepExecutionContext` sent to the worker nodes.

but over on the worker side, where all we have are some Handlers and filters on the JobRunr API side, would we then respond to the leader node about the status of the work? does JobRunr let us encode state and package it up to be delivered to the enqueuing thing? 

* actuator: shows nothing special
* localhost:8000: shows dashboard, which is super useful

* what if i dont have a background server online? buy Pro!
* what if i want to participate in the same DataSource transaction as JobRunr is? buy Pro!
* 
