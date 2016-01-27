package com.lauren.simplenews.model;

import com.lauren.simplenews.beans.ImageBean;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/22
 */
public interface IImageModel {

    Observable getImageList();
}
