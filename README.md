WebDriver Factory for Groovy Scripting in Katalon Studio
========================================================

by kazurayam,
1st edition published March,2019

## Problem to solve

### First problem

A post in the Katalon Forum [Run Automation in the Active browser](https://forum.katalon.com/t/run-automation-in-the-active-browser/19237/4) raised an interesting question. The originator **aguggella** gave us a good explanation what is his/here problem. I would quote the explation with a bit of rewording:

---

>I have Salesforce application which will send OTP (one time password) to the client's browser on the following occasions: (1) first time of logging-in, (2) at the second logging-in and later when the browser does not present credential information associated with the original OTP because the browser's cache is cleared or empty

>I need to open browser manually and login to the Salesforce application once when a new OTP will be processed. I will do that anyway. As long as I use the same Profile of Browser without emptying cached data, the original OTP is retained, and I won't be asked to repeat login operation.

>Problem arises here: I want to fully automate testing this application using Katalon Studio, but I can not. Why? Whenever the automation script is invoked, Katalon executes the script in a new browser with no cached data. So salesforce application always sends a new OTP. New OTP requires manual intervention every time I start test script in Katalon Studio.

---

2. [Open Browser with Custom Profile](https://forum.katalon.com/t/open-browser-with-custom-profile/19268) by Than To.

## Problem to solve

Katalon Studio GUI provides a way to set

## How to run the demo

##
