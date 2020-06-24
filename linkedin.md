# Spring Security OAuth got deprecated. What now ?
Okay, so you're a micro-services developer and, several years ago, you decided
to adopt Spring in general, and Spring Boot/Cloud in particular, as a development
platform. You're a Java fullstack developer, meaning that you're working on the 
back-end side, as well as on the front-end side.

In your previous projects, you have implemented back-end micro-servicves with 
Spring Boot and you decided to use its out-of-the-box support of the Oauth 2.0 
protocol, for authorization purposes and for its RBAC (Role Based Access) 
features. You've found this support very usefull and practical, hence, you'd like
to continue using it in your future projects.

Should this be your case, please continue reading as you might have heard that 
that the mentioned support got deprecated.

## What does this mean, exactly ? 
As a Java developer, even if you don't fully know what deprecation means, you
understand that it doesn't sound good. As a matter of fact, deprecation doesn't 
mean that you won't be able to continue using the given feature as long as you 
keep with the same release. As the Oracle Java SE documentation (https://docs.oracle.com/javase/7/docs/technotes/guides/javadoc/deprecation/deprecation.html)
explains, deprecation means that the given class or package is not longer important.
It is so unimportant that it's being superseeded by another class or package 
and, hence, you're not supposed to use it any more, as it will cease to exist
in the future.

## How bad is it ? 
Of course, you may choose to continue using the deprecated artifact and to take on
your IDE insistent warnings, which make appear your code like a Christmass tree.
To come back to our concern, which is Spring Security Oauth, if you're compiling 
against Spring Boot 2.2.7.RELEASE or older, nothing could prevent you to continue 
using Spring Security OAuth. But if you're like me, i.e. cautious about your code 
quality and, additionally, you want to be able to easily upgrade to future Spring 
Boot releases, then you need to address this issue ASAP. 

It is worth noting however that the deprecation we're talking about here only
concerns the OAuth providers of the Spring Security Oauth project, but not the
consumers. The later are simply moved to Spring Security, as mentioned by the 
OAuth Migration Guide (https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Migration-Guide).

`Since Spring Security doesn’t provide Authorization Server support, migrating a 
Spring Security OAuth Authorization Server is out of scope for this document.`

So, what this means in clear is that, if you relay on OAuth service providers,
as implemnted by Spring Security OAuth project, then don't do it anymore. Just 
manage yourself to find another solution. And don't come asking us how to do it
'cause this is out of our scope.

## Governance, methodology and deontology
As everyone knows, Java is an open-source programming language and computing 
platform. Its real open-source nature is a long and complicated story but at 
least most of its compoenents are available under open-source licences.

The Java governance model is based on Java Community Process (JCP), a program
which aims at standardizing the its specification and development process. Things
might have become a bit different in the last years, with the purchase of Sun
Microsystems by Oracle but, in clear, it's the JCP who decides what a given JVM
release will contain. It's also the JCP responsibility to provide specifications
for all the Java sub-technologies like JDBC (Java Data Base Connection),
JPA (Java Persistance API), JTA (Java Transaction API), JAX-B (Java Architecture
for XML Binding) and dozens of others. All these sub-technologies were included
initially in Java EE (Enterprise Edition) and are currently owned by the Eclipse
Foundation, under the name of Jakarta EE.

Spring, and all its sub-technologies like Spring Boot, Spring Cloud, Spring 
Security, etc., are open-source and Java as well but they obey to a different 
governance model. After a long ans involved history, Spring finished by belonging 
to Pivotal, a private oragnization like Oracle. From this point of view there
is no difference. But as opposed to Java that, before being owned by Oracle, used
to have a very opened a democratic governance model based on community, Spring
is driven by Pivotal in a quite unilateral manner.

As oposed to Java where everything is object of detailed specifications approved
by the comunity through JCP, in the form of JSRs (Java Specification Requests),
Spring doesn't even have any specification. Spring is a set of libraries providing 
the services and the API that its owner decided to provide. And should this owner 
decide to stop providing some services or APIs, then there is nothing you can do
about. 

This is exactly what happened with the Spring Security Oauth project. It has 
simply been decomissioned ! Okay, it seems that further to the users formal 
protests, Pivotal decided that the subject merits further examonation. But as 
per today, there is no final decision. Which, of course, results in deontological 
problems.

## What everything is about ?
Spring Security Oauth project aimes at supplying OAuth 1(a) and OAuth 2.0 providers
and consumers support. This is somehow not completely in line with the Spring 
general philosophy which consists in proposing SPIs (Software Provider Interface)
to almost everything, but not the service providers themself. 

As a matter of fact, if we take for example the transaction management, Spring is
able to interface with your platform's transaction manager via delegates like 
`PlatFormTransactionManager`, `JtaTransactionManager`, etc. But the transaction manager
itself needs to be provided by the platform. 

If we take for example LDAP or `ActiveDirectory`, Spring is giving you all the 
required features such to interface with most of the implementations, but it 
doesn't provide an LDAP service.

There are dozens of other similar examples but the main idea is that Spring,
all sub-technologies included, fulfills the rule "wraps everything, provides
nothing". In this manner, deprecating the Spring Security OAuth project, removing
the OAuth providers and migrating the consumers to Spring Security 5.2.x, makes 
sense.

## What now ?
Okay, since Pivotal has retired the Spring Security Oauth project, leaving in
a daze all the users and developers, another solution is needed. One of the most
suitable one is Keycloak, an open-source IAM (Identity Access Management) solution
maintained by Red Hat. Its commercial counterpart is Red Hat SSO Server.