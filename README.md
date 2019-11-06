# General
## Overview
The Sabre API Code Samples project has been created to give developers creating systems consuming Sabre WebServices some basic examples, code snippets, best practices or even small libraries they could use while developing their code. It shows how to chain simple calls into more complex business cases, where subsequent calls use data returned from the previous ones. The project focuses on such important issues like SOAP session pooling on the client side, or managing the REST authentication token. All the services used and showcased in this project employ the latest versions of the Sabre WebServices at the time of creating this project, that is:

- TravelItineraryRead – version 3.6.0
- PassengersDetails – version 3.2.0
- BargainFinderMax – version 1.9.2
- EnhancedAirBook – version 3.2.0

## Building and configuring the application
Before running the application, one should obtain credentials for SOAP and REST webservices. In order to do it, one should request access to SOAP using Sabre Dev Studio's contact form. Edit the Configuration's src/main/resources/SACSSoapConfig.properties file with the credentials obtained from Sabre; they should be put into the username and password lines. As for now, the credentials are put in the properties file in the plain text to be able to quickly start using the application, but it is highly recommended, that those values are encrypted. The encryption/decryption is provided in the Configuration module of Sabre API Code Samples.

As for REST, the credentials should be encrypted in just the same way as above, but put into the Configuration's src/main/resources/SACSRestConfig.properties file in the corresponding lines.

Build the application using command line and Maven, running the
`mvn clean install`
from the main project folder (sabreapicodesamples).

## Running the application
The application can be run in three different ways.

- Using IDE - After importing the project with all modules to the IDE, one can run the application with FlowTriggerApplication class from soaptrigger module.
- Using commandline java - run the project by executing command
`java -jar flowtrigger-0.0.1-SNAPSHOT.jar`
from the soaptrigger/target folder
- Using Maven – run
`mvn spring-boot:run`
command from the soaptrigger folder.

After application starts, it fills the pool with session that will be used for workflows. The policy here is that one workflow uses the same session throughout the whole process, and after that the session is returned to the pool. If more workflows than the size of the pool are started simultaneously, some of them wait till the running workflows finish and return the session to the pool.

One can run the SOAP workflow by opening http://localhost:8080/orchestratedFlow. It will start a case as follows:

- Bagain Finder Max
- Passenger Details adding passenger name and contact only
- Enhanced Air Book using the first PricedItinerary option returned from Bargain Finder Max call
- Passenger Details adding the rest of the required PNR information
- Travel Itinerary Read

After the workflow is done, the session is being cleaned using IgnoreTransactionLLS call and then returned to the pool.

## Quickstart "How to"
### SOAP
To follow, how the example application works, you should first look into flowtriggers's WorkflowTrigger class. That's the place where the usage of webservices is being triggered. The orchestartedFlow() method initializes a Workflow with PassengerDetailsActivity as its first activity to run. The PassengerDetailsActivity constructs a GenericRequestWrapper with request and response beans as generic parameters. It also creates the next activity that is to be run in this workflow.

```java
@Autowired
private GenericRequestWrapper pd;

@Autowired
private TravelItineraryReadActivity next;
```
The activities' run() method executes the request and returns next activity to be run.

```java
pd.setRequest(getRequestBody());
pd.setLastInFlow(false);
PassengerDetailsRS result = pd.executeRequest(context);
```

So in order to create your own call, one should extend the GenericRequestWrapper class, as described in the Soap section of this document. One executes the call using the executeRequest() method, providing the SharedContext object as a parameter. The SharedContext serves as a state for a sequence of calls. It should contain the ConversationId, as it identifies the session used in the SessionPool.

If one wants to use results from one call in a different call, one should place the response in the SharedContext object.

```java
context.putResult("PassengerDetailsRQ", sw.toString());
context.putResult("PNR",  result.getTravelItineraryReadRS().getTravelItinerary().getItineraryRef().getID());
```

The sessions are being created when the application starts in class SessionPool
```java
for (int i = 0; i < maxSize; i++) {
     context.setConversationId("InitialConversationIdNo_" + (i+1));
     sessionCreateCall.openSession(context);
}
```

In this case, the SessionPoolInterceptor takes care for cleaning the session up and returning it to the pool.

The sessions are being closed by using the shutdown hook in the SoapTriggerApplication's main method and running the destroy() method on the SessionPool.

### REST
To create a REST workflow, one should implement the Workflow’s Activity interface just like in the SOAP case. The call is being injected into GenericRestGetCall or GenericRestPostCall with a request class as the generic type.

```java
@Autowired
GenericRestGetCall<LeadPriceCalendarRequest> call;
```
In case of the GET call, the request class must extend the BaseDomainRequest, as it has a method, which converts the request object’s properties into a query string, which is then attached to the URL that’s being called. The URL should contain the endpoint and the path to the requested service. Both should be set with corresponding methods.

```java
LeadPriceCalendarRequest request = new LeadPriceCalendarRequest.Builder()
	.origin("LAX")
	.destination("JFK")
	.lengthOfStay(5)
	.pointOfSaleCountry("US")
	.build();
call.setUrl(config.getRestProperty("endpoint") + "/v2/shop/flights/fares");
call.setRequest(request);
```
The call is executed using the doCall() method with a response class, and the context as parameters.
```java
LeadPriceCalendarResponse leadPriceCalendar = call.doCall(LeadPriceCalendarResponse.class, context);
```
The first parameter is used to cast the response, the second one for setting the workflow as faulty in case of exception and send it to the ErrorHandler.

In case of the POST call, the procedure is just the same, with one exception – the request class does not need to extend the BaseDomainRequest, as the data are being sent to the service in the body of the request using the RESTTemplate library. See the BargainFinderMaxActivity class for reference.

# Modules
## Configuration
A Spring module responsible for reading configuration files. It consists of three classes and one interface:

- ConfigurationConfig – Spring context configuration class; it’s only responsibility is to enable dependency injection in the module as well as possibility to reuse it in other Spring modules.
- ConfigurationDecoder – an interface defining one method, which requires a string and returns it’s decoded value.
- PasswordProtectedConfigurationDecoder – implementation of ConfigurationDecoder; it uses Java’s Password Based Encryption with MD5 and DES. This algorithm is not the safest available, and it was used as a quick example of usage. Stronger encryption algorithms can be easily plugged in implementing the ConfigurationDecoder.
- SacsConfiguration – main configuration reading class. It offers both encrypted and plain text properties reading. The decoder is being plugged in as an injected dependency that implements the ConfigurationDecoder

## Workflow
A module implementing two design patterns – Workflow and Visitor. The former is used in the soap module for batching single calls to stateful flows which can be used to implement certain use cases. The state is being shared between activities using the SharedContext class instance. The latter is being used for error handling.

- SharedContext – contains a map of results indexed with a name of the call, indicates whether there was a fault during the workflow execution and also defines how many times should the workflow be rerun in case of a failure; implements the Visitable interface, so it can be visited by the error handling visitor.
- Workflow – class used for running use cases.
- Visitor, Visitable – interfaces of the Visitor pattern used in error handling.

## Contract
Maven module, that’s entirely generated during the build process. It contains all the classes needed in the SOAP communication (i. e. requests, responses, headers and faults with their nested members). The class generation is defined in the pom.xml file and uses maven-jaxb2-plugin.

## Error Handler
A module employing the Visitor pattern used to handle faulty workflows. It consists of two classes:

- ErrorHandlingVisitor – class implementing the Visitor pattern. Implemented method is for handling faulty workflows. It simply re-runs them.
- ErrorHandlingSchedule – class which periodically runs the ErrorHandlingVisitor handling on all the failures that are being added to the system failures lists. It checks, whether the limit of reruns has not been exceeded and then uses the ErrorHandlingVisitor to handle the flow errors.

## Rest
A module responsible for executing calls to the Sabre REST webservices. It also utilizes the Workflow module and reads settings from the Configuration module.

The domain model used for REST calls is being created in 3 different ways here:

- Manually - based on the example JSON result from Sabre Developer Studio. This takes a lot of work, but lets the developer choose what should be contained in the class.
- http://www.jsonschema2pojo.org/ - using the web generator - pasting the example JSON result, generating jar with source code and copying classes to the project. Quicker than the manual work, but classes created in the process sometimes contain fields with incorrect type. E. g. if a field in the JSON pasted, contains only numbers, it will recognize it as a numerical type, but class can be edited after the bug detection. Does not handle JSON schema well.
- Maven plugin from http://www.jsonschema2pojo.org/ - generates source code directly in the project. Cleanest and quickest way for domain classes generation. Uses the same mechanism as the former one, but classes are being recreate on every build, so editing them does not make sense. The JSON provided as a template must contain all the possibilities.

The authentication takes place when any unauthenticated call attempts to take place. The TokenHolder class responsible for providing the authentication string checks, whether the token has been created and is not outdated. In any of those two cases, it runs the AuthenticationCall's doCall method, which reads credentials using Configuration module, encodes it in the way described on Sabre Developer Studio and (re)sets the token in the TokenHolder class.

In order to create a workflow, one should write classes implementing Activity interface, in which, depending whether it is POST or GET call, the GenericRestPostCall or GenericRestGetCall should be autowired with request class as a generic indicator.

In case of POST call, the URL should be set and a request object. One runs the call using the doCall() method with a response class as the argument.

In case of GET call, the URL should be set and a request object as well, but the URL should be just the base one for the service. The query part is being appended automatically based on the request object set. One can run the call in just the same way as in the POST case.

## Soap
A module calling webservices using Spring-WS framework using WebServiceTemplate, which order is as follows:

1. Run used callback’s doWithMessage() method
2. Run registered interceptors’ handleRequest() method
3. Send request to the server
4. Run registered interceptors’ handleResponse() or handleFault() method

The most helpful class implemented here is GenericRequestWrapper, which uses two generic types: the request class and the response class. The extenders must provide implementation for five methods:

- interceptors() – method should return a list of ClientInterceptor implementations to be used in addition to the default once, which are FaultInteceptor, LoggingInterceptor and (depending on lastInFlow flag) SessionPoolInterceptor. May return null, if additional interceptors are not required.
- marshaller() – method should return a marshaller which will be used for marshaling/unmarshalling the request/response sent to/received from the server
- callback() – HeaderCallback implementation should be provided in this method. Those provided in this project, will be described in a separate chapter

The request object is provided in the Activity implementation class, as well as setting the lastInFlow flag.

### Callbacks
Used to create headers in messages. They can be divided into two types: those that have to do with session and the other.

Session callbacks:

- SessionCreateHeaderCallback – responsible for creating header containing authentication data (credentials). Used in SessionCreateRQ call.
- SessionCloseHeaderCallback – responsible for creating header which will be used in CloseSessionRQ call. It creates security header based on the Security object provided by calling a setter method.
- PingHeaderCallback – responsible for creating header for a request that refreshes the session. Utilizes Security object provided by calling a setter method. Used in OTA_PingRQ call.

Other callbacks:

- HeaderComposingCallback – responsible for retrieving security object from the session pool, using conversationId of the calling workflow.

### Interceptors
Used to log messages, send faults to handle by error handler, and return sessions to the pool.

### Session pooling
Session creation and closing can be expensive operation. Also, there is a session limit per user set on the server. In order to conserve time on client side and server resources, session pool has been introduced. The size of the pool is configurable in the SACSSoapConfig.properties file in the Configuration module. The heart of this functionality is in the SessionPool class. It is responsible for making calls to open, destroy and refresh sessions that are being kept there. When the HeaderComposingCallback requests a session, it recursively looks up the list of sessions to find a free one and returns it, and if it doesn’t find one, it calls the SessionCreateRQ to create it, only if the pool has space for it. Otherwise, it keeps the call on hold until it can get a free session. When a workflow returns a session to the pool, the IgnoreTransactionRQ is being called, to make sure, the session on the server side is clean. Every ten minutes, the pool calls OTA_PingRQ on every session it keeps, to keep the sessions alive. The sessions are being closed, when the program exits, using an exit hook in the main configuration class (SoapApplicationConfiguration). The pool sends a SessionCloseRQ on every session, that it keeps.

## Flow Trigger
This module is used to run and demonstrate defined workflows using http calls. It’s being used by a small web page based on AngularJS and Bootstap.

One can use the below calls:

- /orchestratedFlow – runs the SOAP workflow, which consists of:
	- BargainFinderMax
	- PassengerDetails (with passenger's name only)
	- EnhancedAirBook (using the first PricedItinerary option returned from BargainFinderMax call)
	- PassengerDetails (with the rest of the required PNR information)
	- TravelItineraryRead (uses the PNR created in the PassengerDetails call)
- /restFlow – runs the REST workflow:
	- LeadPriceCalendar
	- InstaFlight (uses hypermedia link from the LeadPriceCalendar call)
	- BargainFinderMax

# Support

- [Stack Overflow](http://stackoverflow.com/questions/tagged/sabre "Stack Overflow")
- Need to report an issue/improvement? Use the built-in [issues] (https://github.com/SabreDevStudio/SACS-Java/issues) section
- [Sabre Dev Studio](https://developer.sabre.com/)

# Disclaimer of Warranty and Limitation of Liability
This software and any compiled programs created using this software are furnished “as is” without warranty of any kind, including but not limited to the implied warranties of merchantability and fitness for a particular purpose. No oral or written information or advice given by Sabre, its agents or employees shall create a warranty or in any way increase the scope of this warranty, and you may not rely on any such information or advice. Sabre does not warrant, guarantee, or make any representations regarding the use, or the results of the use, of this software, compiled programs created using this software, or written materials in terms of correctness, accuracy, reliability, currentness, or otherwise. The entire risk as to the results and performance of this software and any compiled applications created using this software is assumed by you. Neither Sabre nor anyone else who has been involved in the creation, production or delivery of this software shall be liable for any direct, indirect, consequential, or incidental damages (including damages for loss of business profits, business interruption, loss of business information, and the like) arising out of the use of or inability to use such product even if Sabre has been advised of the possibility of such damages.
