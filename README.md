# nino
NINo (Nanoservice Infrastructure Notation) aims to support services constructed around Functions as a Service with little configuration

Trello site https://trello.com/b/CcoIuVfA/nino-sprint-planning

Slack site https://ninodevelopmentteam.slack.com

Google Drive folder https://drive.google.com/open?id=18HY-YbcuRSbKsikcAsm_YNHsw6o6bYnI

Trello Calendar View https://trello.com/calendar/536147b0197d8e5e3173c884/5d6361be69271e27d4c43d03/b1d5c08412cf59b0758cec4da725bce6.ics

Slite Wiki https://nino-development-team.slite.com

Design Documentation

0 Software Goals

1 Overall Package Diagram

2 Package Descriptions

2.0 nino-manager
This package will take inputs from the command line or other interface, along with one or more files, creates a model of available resources and expected outputs, and then passes required data to an exporter matching its configuration.

edu.jhuapl.nino.manager
control
NinoManager
properties
uiManager  - UiManager
methods
public static void main(String[] args) - this is the main entrance point for execution of NINo
public void processState () - This method will take the NinoState object from this classes UiManager instance and updates the state of various NINo components.
properties
        NinoUiManager ninoUiManager
        NinoExporter[] ninoExporters
        NinoGenerator[] ninoGenerators
functions
        public static void main(String[] args)
        public void processNinoState()

2.1 nino-UI
This package will be the base package that contains all interfaces for required objects and classes related to interactions with users.

edu.jhuapl.nino.ui.view
UiManager
properties
state  - NinoState


2.1.0 nino-desktop-app
This package will host all functionality to provide interfaces for interactions with users. The graphical user interface that it provides will allow users to determine what the collection of functions that will be deployed, how other components can communicate with these functions, and the target deployment environment


2.1.1 nino-cli
This package will be a minimal interface that a user can configure NINo. It will work like nino-desktop-app, but with all interactions from the command line.


2.2 nino-model


This package will hold all common objects that will be shared between NINo’s various packages.

edu.jhuapl.nino.model

NinoState

properties:

processing - boolean - indicates if the state of NINo is still in the middle of processing

functions - List of NinoFunction objects - the metadata about the functions that will be employed to execute

ingressNetworking - Object

egressNetworking - Object

requiredAvailableServices - String List - a list of the required services for this functionality

externalFileReferences - String List - an list of any required files, such as configuration files, pip conf files, etc.

environmentVariables - Map - the key value Map that will assign the appropriate scoped environment variables

templateVariables - Map - the key value Map that will be used to template in values into any code templates

credentials - NinoCredentials - any credentials needed to connect to required services

NinoFunction

inputComms - Comms - enumeration of communication types, which will be associated with input

outputComms - Comms - enumeration of communication types, which will be associated with output

processingCode - String - code that will be used as opposed to generated code

minInstance - int - minimum number of instances that allow horizontal scaling

maxInstance - int - maximum number of instances that allow horizontal scaling

cpuMinInstance - int - minimum number of cpu cores that allow horizontal scaling

cpuMaxInstance - int - maximum number of cpu cores that allow horizontal scaling

library_name - String - the name of the library to retrieve the function from

library_version  - String - the version of the library to retrieve the function from

namespace  - String - the namespace within the library

function  - String - the name of the function within the library

external_signature  - NinoExternalSignature - the object that represents the external signature of the function

inputArgumentConversionType - ConversionType - the enumeration of the types of conversion strategies that will be applied to available data (input, calculated and constant values)

inputArgumentMappingCode - String - if present represents the code that will be placed into the wrapper code for the given function

package - signature

NinoExternalSignature - Abstract class - that represents the various types of external signatures for different systems, such as DB

NinoDbExternalSignature - 

db - String - the name of the database that will hold input to this function

table - String - the name of the table that will hold input to this function

NinoMessageQueueExternalSignature - 

topicName - String - the name of the message queue topic that the capability will subscribe to

NinoRestApiExternalSignature - 

port - int - the integer representing the non-canonical port that will be exposed for the use of the

method - String - the name of the table that will hold the results of the processing

path - String - the part of the complete url used after the host and port. Note that if the method is GET, then each argument to the method will be exposed as a query variable

credentials - NinoCredentials - Interface with implementations for UsernamePassword, SSHKey, etc.

name - String - Name of the type of credential system

NinoUsernamePasswordCredentials - implements NinoCredentials

username - String - the username used to connect to any services

password - String - the password used to connect to any services

NinoSSHKeyCredentials - implements NinoCredentials

sshKey - String - the text of the public ssh key

2.3 nino-input-parser

This package provides the logic for taking input, initially as file, and parsing it into a NINo model of the configuration of everything required to produce a deployment file for an environment such as AWS.

2.4 nino-exporters
This package will be the base package that contains all interfaces for required objects and classes related to exporting a file to be used in the deployment environment.
edu.jhuapl.nino.exporter

NinoExporter - the interface that all code/artifact exporters will implement

methods

void export(NinoState ninoState);


2.4.0 nino-aws-lambda
This package will contain the logic required to export a file to be used in the AWS Lambda deployment environment.


2.4.1 nino-google-functions
This package will contain the logic required to export a file to be used in the Google Functions deployment environment.


2.4.2 nino-docker
This package will contain the logic required to export a file to be used in generating a dockerfile and any required components.


2.4.3 nino-open-faas
This package will contain the logic required to export a file to be used in the deploying one or more functions to an OpenFAAS server.


2.4.4 nino-kube
This package will contain the logic required to export a file to be used in the deploying one or more functions to a Kubernetes cluster as docker containers as part of a Helm chart.


2.5 nino-generator
This is the base package that all output code generators will be based upon. It includes all common objects and interface classes for all generator packages.
edu.jhuapl.nino.generator.control

NinoGenerator - the interface that all code/artifact generators will implement

methods

void generate(NinoState ninoState);

2.5.0 nino-generator-java
This package will hold all logic for creating to take a configuration for a service all code and Java-specific artifacts.


2.5.1 nino-generator-python
This package will hold all logic for creating to take a configuration for a service all code and Python-specific artifacts.


2.6 nino-plugins
This package will be the base package for all plugins for NINo that will can be developed external to the NINo code base, as long as specific interfaces and common object models are followed.


3 Class Model Design

4 Class Interface Design
