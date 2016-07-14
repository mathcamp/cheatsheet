package com.mathcamp.cheatsheet

import java.util.*

/**
 * Created by Chris on 9/2/15.
 */

public data class TimeAmountUnit(val unit: Time.Units, val amount: Int)
public data class DeliniatedTimeUnits(val milliseconds: Int,
                                      val seconds: Int,
                                      val minutes: Int,
                                      val hours: Int,
                                      val days: Int,
                                      val weeks: Int,
                                      val months: Int,
                                      val years: Int)

public object Time {
  // Conversion
  public enum class Units(val value: Long) {
    Milliseconds(1000),
    Seconds(60 * Milliseconds.value),
    Minutes(60 * Seconds.value),
    Hours(24 * Minutes.value),
    Days(7 * Hours.value),
    Weeks(4 * Days.value),
    Months(12 * Weeks.value),
    Years(1 * Months.value)
  }

  public fun millisecondsToSeconds(milliseconds: Long): Long {
    return milliseconds/Units.Seconds.value
  }

  public fun timeUnitsSinceTs(milliseconds: Long): DeliniatedTimeUnits {
    val delta = System.currentTimeMillis() - milliseconds
    return DeliniatedTimeUnits(
        milliseconds = delta.toInt(),
        seconds = (delta / Time.Units.Milliseconds.value).toInt(),
        minutes = (delta / Time.Units.Seconds.value).toInt(),
        hours   = (delta / Time.Units.Minutes.value).toInt(),
        days    = (delta / Time.Units.Hours.value).toInt(),
        weeks   = (delta / Time.Units.Days.value).toInt(),
        months  = (delta / Time.Units.Weeks.value).toInt(),
        years   = (delta / Time.Units.Months.value).toInt()
    )
  }

  // Days of week
  public enum class Day {
    Monday, Tuesday, Wednesday,
    Thursday, Friday, Saturday,
    Sunday
  }

  public fun dayOfWeek(date: Date): Day {
    return when (date.day) {
      1 -> Day.Monday
      2 -> Day.Tuesday
      3 -> Day.Wednesday
      4 -> Day.Thursday
      5 -> Day.Friday
      6 -> Day.Saturday
      7 -> Day.Sunday
      else -> throw MatchException
    }
  }

  // Months of year
  public enum class Month {
    January, February, March,
    April, May, June, July,
    August, September, October,
    November, December
  }

  public fun monthOfYear(date: Date): Month {
    return when (date.month) {
      1 -> Month.January
      2 -> Month.February
      3 -> Month.March
      4 -> Month.April
      5 -> Month.May
      6 -> Month.June
      7 -> Month.July
      8 -> Month.August
      9 -> Month.September
      10 -> Month.October
      11 -> Month.November
      12 -> Month.December
      else -> throw MatchException
    }
  }

  // Years
  public fun getYear(date: Date) = date.year + 1900

  public fun getYearsAgo(date: Date) = Date().year - date.year

  // Utility
  public fun getLargestUnitSince(ts: Long): TimeAmountUnit {
    val timeSince: DeliniatedTimeUnits = Time.timeUnitsSinceTs(ts * 1000)
    val (unit: Units, amount: Int) =
        if (timeSince.years > 0) {
          Units.Years to timeSince.years
        } else if (timeSince.months > 0) {
          Units.Months to timeSince.months
        } else if (timeSince.weeks > 0) {
          Units.Weeks to timeSince.weeks
        } else if (timeSince.days > 0) {
          Units.Days to timeSince.days
        } else if (timeSince.hours > 0) {
          Units.Hours to timeSince.hours
        } else if (timeSince.minutes > 0) {
          Units.Minutes to timeSince.minutes
        } else if (timeSince.seconds > 0) {
          Units.Seconds to timeSince.seconds
        } else {
          Units.Milliseconds to timeSince.milliseconds
        }

    return TimeAmountUnit(unit, amount)
  }
}
