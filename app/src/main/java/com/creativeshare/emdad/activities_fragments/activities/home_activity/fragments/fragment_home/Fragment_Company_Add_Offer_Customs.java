package com.creativeshare.emdad.activities_fragments.activities.home_activity.fragments.fragment_home;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.creativeshare.emdad.R;
import com.creativeshare.emdad.activities_fragments.activities.home_activity.activity.Home_Activity;
import com.creativeshare.emdad.models.CustomClearanceOrderDetailsModel;
import com.creativeshare.emdad.models.UserModel;
import com.creativeshare.emdad.preferences.Preferences;
import com.creativeshare.emdad.remote.Api;
import com.creativeshare.emdad.share.Common;
import com.creativeshare.emdad.tags.Tags;
import com.google.android.material.appbar.AppBarLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Company_Add_Offer_Customs extends Fragment {
    private static final String TAG = "ORDER_ID";
    private static final String TAG2 = "NOTIFICATION_ID";

    private ImageView image_back;
    private LinearLayout ll_back, ll;
    private CircleImageView image;
    private RoundedImageView img_model4, img_commercial, img_tax, img_import, img_custom_card;
    private TextView tv_client_name, tv_order_num, tv_shipment_type;
    private ProgressBar progBar;
    private CoordinatorLayout cord_layout;
    private EditText edt_cost;
    private AppBarLayout app_bar;
    private Button btn_accept, btn_refused;
    private String current_language;
    private UserModel userModel;
    private Preferences preferences;
    private Home_Activity activity;
    private int notification_id;


    private CustomClearanceOrderDetailsModel customClearanceOrderDetailsModel;

    public static Fragment_Company_Add_Offer_Customs newInstance(int order_id, int notification_id) {
        Bundle bundle = new Bundle();
        bundle.putInt(TAG, order_id);
        bundle.putInt(TAG2,notification_id);
        Fragment_Company_Add_Offer_Customs fragment_company_add_offer_water_delivery = new Fragment_Company_Add_Offer_Customs();
        fragment_company_add_offer_water_delivery.setArguments(bundle);
        return fragment_company_add_offer_water_delivery;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_add_offer_customs, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        activity = (Home_Activity) getActivity();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(activity);
        Paper.init(activity);
        current_language = Paper.book().read("lang", Locale.getDefault().getLanguage());
        image_back = view.findViewById(R.id.image_back);
        if (current_language.equals("ar") || current_language.equals("ur")) {
            image_back.setRotation(180.0f);
        }
        tv_shipment_type = view.findViewById(R.id.tv_shipment_type);
        ll_back = view.findViewById(R.id.ll_back);
        ll = view.findViewById(R.id.ll);
        image = view.findViewById(R.id.image);
        tv_client_name = view.findViewById(R.id.tv_client_name);
        tv_order_num = view.findViewById(R.id.tv_order_num);
        img_model4 = view.findViewById(R.id.img_model4);
        img_commercial = view.findViewById(R.id.img_commercial);
        img_tax = view.findViewById(R.id.img_tax);
        img_import = view.findViewById(R.id.img_import);
        img_custom_card = view.findViewById(R.id.img_custom_card);

        progBar = view.findViewById(R.id.progBar);
        progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        cord_layout = view.findViewById(R.id.cord_layout);
        edt_cost = view.findViewById(R.id.edt_cost);
        app_bar = view.findViewById(R.id.app_bar);
        btn_accept = view.findViewById(R.id.btn_accept);
        btn_refused = view.findViewById(R.id.btn_refused);

        app_bar.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int total_range = appBarLayout.getTotalScrollRange();
                if ((total_range + i) > 60) {
                    image.setVisibility(View.VISIBLE);
                    tv_client_name.setVisibility(View.VISIBLE);
                } else {
                    image.setVisibility(View.GONE);
                    tv_client_name.setVisibility(View.GONE);
                }
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            int order_id = bundle.getInt(TAG);
            notification_id = bundle.getInt(TAG2);

            getOrderData(order_id);
        }

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.Back();
            }
        });
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cost = edt_cost.getText().toString().trim();
                if (!TextUtils.isEmpty(cost))
                {
                    edt_cost.setError(null);
                    Common.CloseKeyBoard(activity,edt_cost);
                    Accept(cost);
                }else
                {
                    edt_cost.setError(getString(R.string.field_req));
                }
            }
        });
        btn_refused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Refuse();
            }
        });
    }

    private void Refuse() {

        final ProgressDialog dialog = Common.createProgressDialog(activity,getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();

        Api.getService(Tags.base_url)
                .companyRefuseOrder(notification_id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        dialog.dismiss();
                        if (response.isSuccessful())
                        {
                            Toast.makeText(activity, getString(R.string.suc), Toast.LENGTH_SHORT).show();
                            activity.updateNotificationData();
                        }else
                        {
                            try {
                                Log.e("code_error",response.code()+"_"+response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(activity, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        try {
                            Toast.makeText(activity, getString(R.string.something), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            Log.e("error",t.getMessage());
                        }catch (Exception e)
                        {

                        }


                    }
                });
    }

    private void Accept(String cost) {
        final ProgressDialog dialog = Common.createProgressDialog(activity,getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();

        Api.getService(Tags.base_url)
                .companySendOffer(userModel.getUser().getCompany_information().getId(),customClearanceOrderDetailsModel.getOrder_details().getOrder_id(),String.valueOf(notification_id),cost)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        dialog.dismiss();
                        if (response.isSuccessful())
                        {
                            Toast.makeText(activity, getString(R.string.suc), Toast.LENGTH_SHORT).show();
                            activity.updateNotificationData();
                        }else
                        {
                            try {
                                Log.e("code_error",response.code()+"_"+response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(activity, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        try {
                            Toast.makeText(activity, getString(R.string.something), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            Log.e("error",t.getMessage());
                        }catch (Exception e)
                        {

                        }


                    }
                });

    }

    private void getOrderData(int order_id) {

        Api.getService(Tags.base_url)
                .getCustomClearanceOrderDetails(order_id, Tags.CLEARANCE_ORDER)
                .enqueue(new Callback<CustomClearanceOrderDetailsModel>() {
                    @Override
                    public void onResponse(Call<CustomClearanceOrderDetailsModel> call, Response<CustomClearanceOrderDetailsModel> response) {
                        if (response.isSuccessful()) {
                            progBar.setVisibility(View.GONE);
                            updateUI(response.body());
                        } else {
                            try {
                                Log.e("code_error", response.code() + "_" + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(activity, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CustomClearanceOrderDetailsModel> call, Throwable t) {
                        Log.e("error", t.getMessage());
                    }
                });

    }

    private void updateUI(CustomClearanceOrderDetailsModel customClearanceOrderDetailsModel) {
        this.customClearanceOrderDetailsModel = customClearanceOrderDetailsModel;
        Picasso.with(activity).load(Uri.parse(Tags.base_url + customClearanceOrderDetailsModel.getOrder().getFrom_user_image())).placeholder(R.drawable.logo_512).fit().into(image);
        cord_layout.setVisibility(View.VISIBLE);
        ll.setVisibility(View.VISIBLE);
        tv_client_name.setText(customClearanceOrderDetailsModel.getOrder().getFrom_user_name());
        tv_order_num.setText(String.format("%s %s", "#", customClearanceOrderDetailsModel.getOrder_details().getOrder_id()));
        tv_shipment_type.setText(customClearanceOrderDetailsModel.getOrder_details().getDescription());

        Picasso.with(activity).load(Uri.parse(Tags.custom_url+customClearanceOrderDetailsModel.getOrder_details().getModelFour())).fit().into(img_model4);
        Picasso.with(activity).load(Uri.parse(Tags.custom_url+customClearanceOrderDetailsModel.getOrder_details().getCommercialRegister())).fit().into(img_commercial);
        Picasso.with(activity).load(Uri.parse(Tags.custom_url+customClearanceOrderDetailsModel.getOrder_details().getMultiplicationCard())).fit().into(img_tax);
        Picasso.with(activity).load(Uri.parse(Tags.custom_url+customClearanceOrderDetailsModel.getOrder_details().getImportCard())).fit().into(img_import);
        Picasso.with(activity).load(Uri.parse(Tags.custom_url+customClearanceOrderDetailsModel.getOrder_details().getSoshibalCard())).fit().into(img_custom_card);

    }
}
