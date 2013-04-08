# datetime-logic


### What is this?

This is a date (and later, time) library for Clojure that uses core.logic.

### Why is this?

Mainly as an excuse for me to play with Clojure's core.logic.  Calendars seem like a great domain to experiment with logic programming.  I hope that this becomes something useful, serving as another practical example of using core.logic in "the real world".

### What can it do?

You can create intersting queries against the Gregorian calendar.  Eventually soon it will allow you to build queries that involve multiple calendars.

## Usage

This is actively being developed and so subject to frequent changes.  Here are some examples, and there are many more in the tests.

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
- [Calendrical Calculations](http://emr.cs.iit.edu/home/reingold/calendar-book/third-edition/) Provides an algorithmic explanation of several calendars.
- The many lectures of David Nolen, Daniel Friedman and William Byrd.
- The Clojure/West 2013 miniKanren Confo.

## License

Copyright © 2013 Stephen Sloan

Distributed under the Eclipse Public License, the same as Clojure.
