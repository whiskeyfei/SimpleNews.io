package com.lauren.simplenews.presenter;

import com.lauren.simplenews.beans.ImageBean;
import com.lauren.simplenews.event.IImageModel;
import com.lauren.simplenews.event.IImagePresenter;
import com.lauren.simplenews.model.ImageModel;
import com.lauren.simplenews.view.IImageView;
import com.whiskeyfei.mvp.base.BasePresenter;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class ImagePresenter extends BasePresenter<IImageView> implements IImagePresenter {
    private static final String TAG = "ImagePresenter";
    private IImageModel mImageModel;
    private Subscription mSubscription;

    public ImagePresenter(IImageView imageView) {
        attachView(imageView);
        mImageModel = new ImageModel();
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void loadImageList() {
        checkViewAttached();
        mSubscription = mImageModel.getImageList()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        getMvpBaseView().showProgress();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ImageBean>>() {
                    @Override
                    public void onCompleted() {
                        getMvpBaseView().hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpBaseView().hideProgress();
                        getMvpBaseView().showLoadFailMsg();
                    }

                    @Override
                    public void onNext(List<ImageBean> list) {
                        getMvpBaseView().onSuccess(list);
                    }
                });
    }


}
