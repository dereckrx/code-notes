---
title: DDD Code Organization
date: 2020-05-20
tags: [ddd]
---

## Where to package a file
Identify
* Layer: Interface/Trigger, App/use-case, Domain, infra/client
	- Is it an input into your app? Controller? Event Consumer? Job?
	- Is it an app use case or service? That holds app business logic?
	- Is it a domain use case or service? That holds company business logic?
	- does it talk directly with a 3rd party service or database?
* Component: domain entity folder, either App or Business Domain Entity
* Feature:  App Use-case, ex: openNewAccount

Layer: controller
Component: Account
Feature: Open Account

Where is the best place to put, so that bad dependencies are made difficult, but 

## Layer
interface
- http/account/accountController
app
- account/openNewAccount/openNewaccountUseCase
domain
- account/openNewAccount/creditCheckService
* infra

## Component
account
- interface
- app/openNewAccount/openNewAccountUseCase
- domain/openNewAccount/creditCheckService
- infra
Payments

## Feature
account
- openNewAccount
	- interface
	- openNewAccountUseCase
	- creditCheckSerivce
	- infra
Payments