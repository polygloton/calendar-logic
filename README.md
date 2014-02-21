# calendar-logic


This is a calendar library for Clojure that uses core.logic.  You can create interesting queries against the Gregorian calendar.  (In the future I hope to add the ability to build queries that involve multiple calendars).

## Usage

Here are some examples, and there are many more in the tests.

### Find months in 2013 where Friday falls on the 13th

```clojure
(use 'clojure.core.logic)
(use 'datetime-logic.gregorian)

(run* [month]
  (day-of-the-week 2013 month 13 :friday))
```

Results in __(9 12)__.

### What day is Thanksgiving (US) in 2013?

```clojure
(use 'clojure.core.logic)
(use 'datetime-logic.us-holidays)

(run* [day]
  (thanksgiving-day 2013 11 day))
```

Results in __(28)__.

### Which federal holidays (US) are on Monday in 2013?

```clojure
(use 'clojure.core.logic)
(use 'datetime-logic.us-holidays)
(use 'datetime-logic.gregorian)

(run* [holiday]
  (fresh [month day]
    (federal-holiday 2013 month day holiday)
    (day-of-the-week 2013 month day :monday)))
```

Results in __(:mlk-bday :washington-bday :veterans-day :memorial-day :labor-day :columbus-day)__, which is only really interesting because it includes veterans day (defined as Nov 11th rather than a Monday).

## Inspiration

These are the inspirations influencing this project.

- [The Reasoned Schemer](http://mitpress.mit.edu/books/reasoned-schemer) Taught me relational programming
- [Calendrical Calculations](http://emr.cs.iit.edu/home/reingold/calendar-book/third-edition/) Provides an algorithmic explanation of __thirty__ calendars.
- The many lectures of David Nolen, Daniel Friedman and William Byrd.
- The Clojure/West 2013 miniKanren Confo.

## License

Copyright © 2013 Stephen Sloan

Distributed under the Eclipse Public License, the same as Clojure.
