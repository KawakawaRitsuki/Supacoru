package com.KawakawaPlanning.supacoru.http;

import java.util.EventListener;

/**
 * Created by KP on 15/11/24.
 */
public interface OnHttpErrorListener extends EventListener {

    void onError(int error);

}