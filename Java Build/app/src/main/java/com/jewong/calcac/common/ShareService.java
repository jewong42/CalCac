package com.jewong.calcac.common;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.pdf.PdfDocument;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ShareService {

    Context context;
    View view;

    public ShareService(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public File getFile() {
        if (context == null) return null;
        final PdfDocument document = new PdfDocument();
        final PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(
                view.getWidth(),
                view.getHeight(), 1).create();
        final PdfDocument.Page page = getPage(document, pageInfo);
        document.finishPage(page);
        try {
            final ContextWrapper contextWrapper = new ContextWrapper(context);
            final File directory = contextWrapper.getDir(context.getFilesDir().getName(), Context.MODE_PRIVATE);
            final File file = new File(directory, "account_info" + System.currentTimeMillis() + ".pdf");
            final FileOutputStream fos = new FileOutputStream(file);
            document.writeTo(fos);
            document.close();
            fos.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PdfDocument.Page getPage(PdfDocument document, PdfDocument.PageInfo pageInfo) {
        if (context == null) return null;
        final PdfDocument.Page page = document.startPage(pageInfo);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(pageInfo.getPageWidth(), View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(pageInfo.getPageHeight(), View.MeasureSpec.EXACTLY);
        view.measure(measuredWidth, measuredHeight);
        view.draw(page.getCanvas());
        return page;
    }

}