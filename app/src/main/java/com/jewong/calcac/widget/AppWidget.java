package com.jewong.calcac.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import com.jewong.calcac.R;
import com.jewong.calcac.common.AppExecutors;
import com.jewong.calcac.common.MathUtils;
import com.jewong.calcac.common.StringUtils;
import com.jewong.calcac.data.entity.User;
import com.jewong.calcac.model.AppDatabase;
import com.jewong.calcac.ui.MainActivity;

public class AppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            AppDatabase mAppDatabase = AppDatabase.getInstance(context);
            User user = mAppDatabase.userDao().loadUserValue();
            AppExecutors.getInstance().mainThread().execute(() -> {
                RemoteViews views = getViews(context, user);
                appWidgetManager.updateAppWidget(appWidgetId, views);
            });
        });
    }

    private static RemoteViews getViews(Context context, User user) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        if (user != null) {
            String g = context.getString(R.string.gram);
            String calories = StringUtils.formatLength(String.valueOf(MathUtils.getCalories(user)), 8);
            String carbohydrates = StringUtils.formatLength(String.valueOf(MathUtils.getCarbohydrates(user)), 8) + g;
            String protein = StringUtils.formatLength(String.valueOf(MathUtils.getProtein(user)), 8) + g;
            String fat = StringUtils.formatLength(String.valueOf(MathUtils.getFats(user)), 8) + g;
            views.setViewVisibility(R.id.data_container, View.VISIBLE);
            views.setViewVisibility(R.id.error_container, View.GONE);
            views.setTextViewText(R.id.calories_text_view, calories);
            views.setTextViewText(R.id.carb_text_view, carbohydrates);
            views.setTextViewText(R.id.protein_text_view, protein);
            views.setTextViewText(R.id.fat_text_view, fat);
        } else {
            views.setViewVisibility(R.id.data_container, View.GONE);
            views.setViewVisibility(R.id.error_container, View.VISIBLE);
            views.setOnClickPendingIntent(R.id.error_container, pendingIntent);
        }
        return views;
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget_preview is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget_preview is disabled
    }
}