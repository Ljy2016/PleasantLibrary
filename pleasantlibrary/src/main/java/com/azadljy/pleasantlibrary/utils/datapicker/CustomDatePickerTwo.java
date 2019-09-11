package com.azadljy.pleasantlibrary.utils.datapicker;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.azadljy.pleasantlibrary.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class CustomDatePickerTwo {

    /**
     * 定义结果回调接口
     */
    public interface ResultHandler {
        void handle(String startTime, String endTime);
    }

    public enum SCROLL_TYPE {
        HOUR(1),
        MINUTE(2);

        SCROLL_TYPE(int value) {
            this.value = value;
        }

        public int value;
    }

    private int scrollUnits = SCROLL_TYPE.HOUR.value + SCROLL_TYPE.MINUTE.value;
    private ResultHandler handler;
    private Context context;
    private boolean canAccess = false;

    private Dialog datePickerDialog;
    private DatePickerView year_pv_start, month_pv_start, day_pv_start;
    private DatePickerView year_pv_end, month_pv_end, day_pv_end;

    private static final int MAX_MONTH = 12;

    private ArrayList<String> start_year, start_month, start_day, end_year, end_month, end_day;
    private int startYear, startMonth, startDay, endYear, endMonth, endDay;
    private int startYear1, startMonth1, startDay1, endYear1, endMonth1, endDay1;
    private boolean spanYear, spanMon, spanDay;
    private boolean spanYear1, spanMon1, spanDay1;
    private Calendar selectedCalender, startCalendar, endCalendar;
    private Calendar selectedCalender1, startCalendar1, endCalendar1;
    private TextView tv_cancle, tv_select;
    private String timeStyle = "yyyy-MM-dd";

    public CustomDatePickerTwo(Context context, ResultHandler resultHandler, String startDate, String endDate) {
        if (isValidDate(startDate, "yyyy-MM-dd") && isValidDate(endDate, "yyyy-MM-dd")) {
            canAccess = true;
            this.context = context;
            this.handler = resultHandler;
            selectedCalender = Calendar.getInstance();
            startCalendar = Calendar.getInstance();
            endCalendar = Calendar.getInstance();
            selectedCalender1 = Calendar.getInstance();
            startCalendar1 = Calendar.getInstance();
            endCalendar1 = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            try {
                startCalendar.setTime(sdf.parse(startDate));
                endCalendar.setTime(sdf.parse(endDate));
                startCalendar1.setTime(sdf.parse(startDate));
                endCalendar1.setTime(sdf.parse(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            initDialog();
            initView();
        }
    }

    private void initDialog() {
        if (datePickerDialog == null) {
            datePickerDialog = new Dialog(context, R.style.time_dialog);
            datePickerDialog.setCancelable(false);
            datePickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            datePickerDialog.setContentView(R.layout.custom_date_picker_two);
            Window window = datePickerDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = dm.widthPixels;
            window.setAttributes(lp);
        }
    }

    private void initView() {
        year_pv_start = (DatePickerView) datePickerDialog.findViewById(R.id.year_pv);
        month_pv_start = (DatePickerView) datePickerDialog.findViewById(R.id.month_pv);
        day_pv_start = (DatePickerView) datePickerDialog.findViewById(R.id.day_pv);

        year_pv_end = (DatePickerView) datePickerDialog.findViewById(R.id.year_pv_end);
        month_pv_end = (DatePickerView) datePickerDialog.findViewById(R.id.month_pv_end);
        day_pv_end = (DatePickerView) datePickerDialog.findViewById(R.id.day_pv_end);

        tv_cancle = (TextView) datePickerDialog.findViewById(R.id.tv_cancle);
        tv_select = (TextView) datePickerDialog.findViewById(R.id.tv_select);


        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.dismiss();
            }
        });

        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat(timeStyle, Locale.CHINA);
                handler.handle(sdf.format(selectedCalender.getTime()), sdf.format(selectedCalender1.getTime()));
                datePickerDialog.dismiss();
            }
        });
    }

    private void initParameter() {
        startYear = startCalendar.get(Calendar.YEAR);
        startMonth = startCalendar.get(Calendar.MONTH) + 1;
        startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
        endYear = endCalendar.get(Calendar.YEAR);
        endMonth = endCalendar.get(Calendar.MONTH) + 1;
        endDay = endCalendar.get(Calendar.DAY_OF_MONTH);

        startYear1 = startCalendar1.get(Calendar.YEAR);
        startMonth1 = startCalendar1.get(Calendar.MONTH) + 1;
        startDay1 = startCalendar1.get(Calendar.DAY_OF_MONTH);
        endYear1 = endCalendar1.get(Calendar.YEAR);
        endMonth1 = endCalendar1.get(Calendar.MONTH) + 1;
        endDay1 = endCalendar1.get(Calendar.DAY_OF_MONTH);

        spanYear = startYear != endYear;
        spanMon = (!spanYear) && (startMonth != endMonth);
        spanDay = (!spanMon) && (startDay != endDay);

        spanYear1 = startYear1 != endYear1;
        spanMon1 = (!spanYear1) && (startMonth1 != endMonth1);
        spanDay1 = (!spanMon1) && (startDay1 != endDay1);

        selectedCalender.setTime(startCalendar.getTime());
        selectedCalender1.setTime(startCalendar1.getTime());

    }

    private void initTimer() {
        initArrayList();
        if (spanYear) {
            for (int i = startYear; i <= endYear; i++) {
                start_year.add(String.valueOf(i));
            }
            for (int i = startMonth; i <= MAX_MONTH; i++) {
                start_month.add(formatTimeUnit(i));
            }
            for (int i = startDay; i <= startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                start_day.add(formatTimeUnit(i));
            }


        } else if (spanMon) {
            start_year.add(String.valueOf(startYear));
            for (int i = startMonth; i <= endMonth; i++) {
                start_month.add(formatTimeUnit(i));
            }
            for (int i = startDay; i <= startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                start_day.add(formatTimeUnit(i));
            }

        } else if (spanDay) {
            start_year.add(String.valueOf(startYear));
            start_month.add(formatTimeUnit(startMonth));
            for (int i = startDay; i <= endDay; i++) {
                start_day.add(formatTimeUnit(i));
            }
        }


        if (spanYear1) {
            for (int i = startYear1; i <= endYear1; i++) {
                end_year.add(String.valueOf(i));
            }
            for (int i = startMonth1; i <= MAX_MONTH; i++) {
                end_month.add(formatTimeUnit(i));
            }
            for (int i = startDay1; i <= startCalendar1.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                end_day.add(formatTimeUnit(i));
            }


        } else if (spanMon1) {
            end_year.add(String.valueOf(startYear1));
            for (int i = startMonth1; i <= endMonth1; i++) {
                end_month.add(formatTimeUnit(i));
            }
            for (int i = startDay1; i <= startCalendar1.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                end_day.add(formatTimeUnit(i));
            }

        } else if (spanDay1) {
            end_year.add(String.valueOf(startYear1));
            end_month.add(formatTimeUnit(startMonth1));
            for (int i = startDay1; i <= endDay1; i++) {
                end_day.add(formatTimeUnit(i));
            }
        }
        loadComponent();
    }

    /**
     * 将“0-9”转换为“00-09”
     */
    private String formatTimeUnit(int unit) {
        return unit < 10 ? "0" + String.valueOf(unit) : String.valueOf(unit);
    }

    private void initArrayList() {
        if (start_year == null) start_year = new ArrayList<>();
        if (start_month == null) start_month = new ArrayList<>();
        if (start_day == null) start_day = new ArrayList<>();

        if (end_year == null) end_year = new ArrayList<>();
        if (end_month == null) end_month = new ArrayList<>();
        if (end_day == null) end_day = new ArrayList<>();

        start_year.clear();
        start_month.clear();
        start_day.clear();

        end_year.clear();
        end_month.clear();
        end_day.clear();

    }

    private void loadComponent() {
        year_pv_start.setData(start_year);
        month_pv_start.setData(start_month);
        day_pv_start.setData(start_day);

        year_pv_start.setSelected(0);
        month_pv_start.setSelected(0);
        day_pv_start.setSelected(0);

        year_pv_end.setData(end_year);
        month_pv_end.setData(end_month);
        day_pv_end.setData(end_day);

        year_pv_end.setSelected(0);
        month_pv_end.setSelected(0);
        day_pv_end.setSelected(0);

        executeScroll();
    }

    private void addListener() {
        year_pv_start.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender.set(Calendar.YEAR, Integer.parseInt(text));
                monthChange();
            }
        });

        month_pv_start.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender.set(Calendar.DAY_OF_MONTH, 1);
                selectedCalender.set(Calendar.MONTH, Integer.parseInt(text) - 1);
                dayChange();
            }
        });

        day_pv_start.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(text));
            }
        });

        year_pv_end.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender1.set(Calendar.YEAR, Integer.parseInt(text));
                endMonthChange();
            }
        });

        month_pv_end.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender1.set(Calendar.DAY_OF_MONTH, 1);
                selectedCalender1.set(Calendar.MONTH, Integer.parseInt(text) - 1);
                endDayChange();
            }
        });

        day_pv_end.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectedCalender1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(text));
            }
        });

    }

    //todo

    private void monthChange() {
        start_month.clear();
        int selectedYear = selectedCalender.get(Calendar.YEAR);
        if (selectedYear == startYear) {
            for (int i = startMonth; i <= MAX_MONTH; i++) {
                start_month.add(formatTimeUnit(i));
            }
        } else if (selectedYear == endYear) {
            for (int i = 1; i <= endMonth; i++) {
                start_month.add(formatTimeUnit(i));
            }
        } else {
            for (int i = 1; i <= MAX_MONTH; i++) {
                start_month.add(formatTimeUnit(i));
            }
        }
        selectedCalender.set(Calendar.MONTH, Integer.parseInt(start_month.get(0)) - 1);
        month_pv_start.setData(start_month);
        month_pv_start.setSelected(0);
        executeAnimator(month_pv_start);

        month_pv_start.postDelayed(new Runnable() {
            @Override
            public void run() {
                dayChange();
            }
        }, 100);
    }

    private void endMonthChange() {
        end_month.clear();
        int selectedYear = selectedCalender1.get(Calendar.YEAR);
        if (selectedYear == startYear1) {
            for (int i = startMonth1; i <= MAX_MONTH; i++) {
                end_month.add(formatTimeUnit(i));
            }
        } else if (selectedYear == endYear1) {
            for (int i = 1; i <= endMonth1; i++) {
                end_month.add(formatTimeUnit(i));
            }
        } else {
            for (int i = 1; i <= MAX_MONTH; i++) {
                end_month.add(formatTimeUnit(i));
            }
        }
        selectedCalender1.set(Calendar.MONTH, Integer.parseInt(end_month.get(0)) - 1);
        month_pv_end.setData(end_month);
        month_pv_end.setSelected(0);
        executeAnimator(month_pv_end);

        month_pv_end.postDelayed(new Runnable() {
            @Override
            public void run() {
                endDayChange();
            }
        }, 100);
    }

    private void endDayChange() {
        end_day.clear();
        int selectedYear = selectedCalender1.get(Calendar.YEAR);
        int selectedMonth = selectedCalender1.get(Calendar.MONTH) + 1;
        if (selectedYear == startYear1 && selectedMonth == startMonth1) {
            for (int i = startDay1; i <= selectedCalender1.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                end_day.add(formatTimeUnit(i));
            }
        } else if (selectedYear == endYear1 && selectedMonth == endMonth1) {
            for (int i = 1; i <= endDay1; i++) {
                end_day.add(formatTimeUnit(i));
            }
        } else {
            for (int i = 1; i <= selectedCalender1.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                end_day.add(formatTimeUnit(i));
            }
        }
        selectedCalender1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(end_day.get(0)));
        day_pv_end.setData(end_day);
        day_pv_end.setSelected(0);
        executeAnimator(day_pv_end);
    }

    private void dayChange() {
        start_day.clear();
        int selectedYear = selectedCalender.get(Calendar.YEAR);
        int selectedMonth = selectedCalender.get(Calendar.MONTH) + 1;
        if (selectedYear == startYear && selectedMonth == startMonth) {
            for (int i = startDay; i <= selectedCalender.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                start_day.add(formatTimeUnit(i));
            }
        } else if (selectedYear == endYear && selectedMonth == endMonth) {
            for (int i = 1; i <= endDay; i++) {
                start_day.add(formatTimeUnit(i));
            }
        } else {
            for (int i = 1; i <= selectedCalender.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                start_day.add(formatTimeUnit(i));
            }
        }
        selectedCalender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(start_day.get(0)));
        day_pv_start.setData(start_day);
        day_pv_start.setSelected(0);
        executeAnimator(day_pv_start);
    }


    private void executeAnimator(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.3f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.3f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(200).start();
    }

    private void executeScroll() {
        year_pv_start.setCanScroll(start_year.size() > 1);
        month_pv_start.setCanScroll(start_month.size() > 1);
        day_pv_start.setCanScroll(start_day.size() > 1);
        year_pv_end.setCanScroll(end_year.size() > 1);
        month_pv_end.setCanScroll(end_month.size() > 1);
        day_pv_end.setCanScroll(end_day.size() > 1);
    }

    private int disScrollUnit(SCROLL_TYPE... scroll_types) {
        if (scroll_types == null || scroll_types.length == 0) {
            scrollUnits = SCROLL_TYPE.HOUR.value + SCROLL_TYPE.MINUTE.value;
        } else {
            for (SCROLL_TYPE scroll_type : scroll_types) {
                scrollUnits ^= scroll_type.value;
            }
        }
        return scrollUnits;
    }

    public void show(String time, String endTime) {
        if (canAccess) {
            if (isValidDate(time, "yyyy-MM-dd") && isValidDate(endTime, "yyyy-MM-dd")) {
                if (startCalendar.getTime().getTime() < endCalendar.getTime().getTime() && startCalendar1.getTime().getTime() < endCalendar1.getTime().getTime()) {
                    canAccess = true;
                    initParameter();
                    initTimer();
                    addListener();
                    setSelectedTime(time, endTime);
                    datePickerDialog.show();
                }
            } else {
                canAccess = false;
            }
        }
    }


    /**
     * 设置日期控件默认选中的时间
     */
    public void setSelectedTime(String time, String time1) {
        Log.e("AppTAG", "setSelectedTime: " + time + "---" + time1);
        if (canAccess) {
            String[] str = time.split(" ");
            String[] dateStr = str[0].split("-");
            String[] str1 = time1.split(" ");
            String[] dateStr1 = str1[0].split("-");

            year_pv_start.setSelected(dateStr[0]);
            year_pv_end.setSelected(dateStr1[0]);
            selectedCalender.set(Calendar.YEAR, Integer.parseInt(dateStr[0]));
            selectedCalender1.set(Calendar.YEAR, Integer.parseInt(dateStr1[0]));
            start_month.clear();
            end_month.clear();
            int selectedYear = selectedCalender.get(Calendar.YEAR);
            if (selectedYear == startYear) {
                for (int i = startMonth; i <= MAX_MONTH; i++) {
                    start_month.add(formatTimeUnit(i));
                }
            } else if (selectedYear == endYear) {
                for (int i = 1; i <= endMonth; i++) {
                    start_month.add(formatTimeUnit(i));
                }
            } else {
                for (int i = 1; i <= MAX_MONTH; i++) {
                    start_month.add(formatTimeUnit(i));
                }
            }
            int selectedYear1 = selectedCalender1.get(Calendar.YEAR);
            if (selectedYear1 == startYear1) {
                for (int i = startMonth1; i <= MAX_MONTH; i++) {
                    end_month.add(formatTimeUnit(i));
                }
            } else if (selectedYear1 == endYear1) {
                for (int i = 1; i <= endMonth1; i++) {
                    end_month.add(formatTimeUnit(i));
                }
            } else {
                for (int i = 1; i <= MAX_MONTH; i++) {
                    end_month.add(formatTimeUnit(i));
                }
            }
            month_pv_start.setData(start_month);
            month_pv_start.setSelected(dateStr[1]);
            selectedCalender.set(Calendar.MONTH, Integer.parseInt(dateStr[1]) - 1);
            executeAnimator(month_pv_start);
            start_day.clear();

            month_pv_end.setData(end_month);
            month_pv_end.setSelected(dateStr1[1]);
            selectedCalender1.set(Calendar.MONTH, Integer.parseInt(dateStr1[1]) - 1);
            executeAnimator(month_pv_end);
            end_day.clear();
            int selectedMonth = selectedCalender.get(Calendar.MONTH) + 1;
            if (selectedYear == startYear && selectedMonth == startMonth) {
                for (int i = startDay; i <= selectedCalender.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                    start_day.add(formatTimeUnit(i));
                }
            } else if (selectedYear == endYear && selectedMonth == endMonth) {
                for (int i = 1; i <= endDay; i++) {
                    start_day.add(formatTimeUnit(i));
                }
            } else {
                for (int i = 1; i <= selectedCalender.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                    start_day.add(formatTimeUnit(i));
                }
            }

            int selectedMonth1 = selectedCalender1.get(Calendar.MONTH) + 1;
            if (selectedYear1 == startYear1 && selectedMonth1 == startMonth1) {
                for (int i = startDay1; i <= selectedCalender1.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                    end_day.add(formatTimeUnit(i));
                }
            } else if (selectedYear == endYear1 && selectedMonth == endMonth1) {
                for (int i = 1; i <= endDay1; i++) {
                    end_day.add(formatTimeUnit(i));
                }
            } else {
                for (int i = 1; i <= selectedCalender1.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                    end_day.add(formatTimeUnit(i));
                }
            }
            day_pv_start.setData(start_day);
            day_pv_start.setSelected(dateStr[2]);
            selectedCalender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr[2]));
            executeAnimator(day_pv_start);

            day_pv_end.setData(end_day);
            day_pv_end.setSelected(dateStr1[2]);
            selectedCalender1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr1[2]));
            executeAnimator(day_pv_end);
            executeScroll();
        }
    }

    /**
     * 验证字符串是否是一个合法的日期格式
     */
    private boolean isValidDate(String date, String template) {
        boolean convertSuccess = true;
        // 指定日期格式
        SimpleDateFormat format = new SimpleDateFormat(template, Locale.CHINA);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2015/02/29会被接受，并转换成2015/03/01
            format.setLenient(false);
            format.parse(date);
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            Log.e("AppTAG", "isValidDate: " + e.getMessage());
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public String getTimeStyle() {
        return timeStyle;
    }

    public void setTimeStyle(String timeStyle) {
        this.timeStyle = timeStyle;
    }
}
