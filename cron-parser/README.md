# **Cron Parser**

It is a command line application which parses a cron string and expands each field
to show the times at which it will run. You may use whichever language you feel most
comfortable with.

We are only consider the standard cron format with five time fields (minute, hour, day of
month, month, and day of week) plus a command, and no need to handle the special
time strings such as "@yearly". 

The input will be on a single line.
The cron string will be passed to your application as a single argument.

~$ your-program "*/15 0 1,15 * 1-5 /usr/bin/find"

The output should be formatted as a table with the field name taking the first 14 columns and
the times as a space-separated list following it.

For example, the following input argument:

*/15 0 1,15 * 1-5 /usr/bin/find

Should yield the following output:\

minute 0 15 30 45\
hour 0\
day of month 1 15\
month 1 2 3 4 5 6 7 8 9 10 11 12\
day of week 1 2 3 4 5\
command /usr/bin/find

**Limitations**

1. This does not handle the special time strings such as "@yearly"
2. This does not handle if the month has less than 31 days or not

**Instructions to run the program**

$ sh run.sh "5 0-4/2 * 8 * /usr/bin/find"\
The shell script compiles the java files and run the jar in target directory.

For example, the following input argument:

sh run.sh "5 0-4/2 * 8 * /usr/bin/find"

Yields the following output:

Cron Expression entered : 5 0-4/2 * 8 * /usr/bin/find\
minute        5\
hour          0 2 4\
day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31\
month         8\
day of week   0 1 2 3 4 5 6\
command       /usr/bin/find

**Tests**

All junit test cases are written in test dir. It covers almost all the test case. ALl the test case will run when the build will get triggered.