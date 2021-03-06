package com.example.mobilhotelqr.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.time.ZonedDateTime;
import java.util.Calendar;

import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.DashboardFragment;
import com.example.mobilhotelqr.PojoModels.AlarmResponse;
import com.example.mobilhotelqr.PojoModels.LoginUserAfter.LoginUserAfter;
import com.example.mobilhotelqr.R;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAlarm extends Fragment {

    private View mViev;
    private Button select_date;
    Context context ;
    private TextView date;
    private RetrofitProcess retrofitProcess;
    SharedPreferences mPrefs ;

    public FragmentAlarm( Context context) {
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViev = inflater.inflate(R.layout.fragment_alarm,container,false);
        date = mViev.findViewById(R.id.date);

                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int hour =calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                select_date=mViev.findViewById(R.id.select_date);


                 DatePickerDialog dpd = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {



                                month += 1;  // aylar dizi olarak 0'dan ba??lad?????? i??in 1 art??r??larak yaz??ld??.
                                String days="";
                                if (dayOfMonth<10)
                                    days="0"+dayOfMonth;
                                else
                                    days=String.valueOf(dayOfMonth);

                                String months="";
                                if(month<10)
                                    months="0"+month;
                                else
                                    months=String.valueOf(month);

                                String finalMonths = months;
                                String finalDays = days;
                                TimePickerDialog tpd = new TimePickerDialog(context,
                                        new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hour, int minute) {

                                        String hours="";
                                        if (hour<10)
                                            hours="0"+hour;
                                        else
                                            hours=String.valueOf(hour);

                                        String min ="";
                                        if(minute<10)
                                            min="0"+minute;
                                        else
                                            min=String.valueOf(minute);

                                        date.setText(year + "/"+ finalMonths +"/" +finalDays +" "+hours+':'+min);
                                    }
                                },hour,minute,true);
                                tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Ok", tpd);
                                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (i == DialogInterface.BUTTON_NEGATIVE) {
                                            Fragment fragment = new DashboardFragment();
                                            getFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
                                        }
                                    }
                                });
                                tpd.setCancelable(false);
                                tpd.show();
                            }
                        }, year, month, dayOfMonth);

                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ok", dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == DialogInterface.BUTTON_NEGATIVE) {
                            Fragment fragment = new DashboardFragment();
                            getFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
                        }
                    }
                });
                dpd.setCancelable(false);
                dpd.show();



         select_date.setOnClickListener(new View.OnClickListener() {
           @Override
         public void onClick(View view) {

               Gson gson = new Gson();
               mPrefs = getActivity().getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
               String jsonn = mPrefs.getString("User", "");
               LoginUserAfter user = gson.fromJson(jsonn,LoginUserAfter.class);


               // buraya servis komutlar?? yaz??lacak. Butona t??klay??nca resepsiyona g??nderilecek kodlar burada olacak.
               String deneme = date.getText().toString();
               retrofitProcess = ApiUtils.setAlarm();
               retrofitProcess.setAlarm(deneme,user.getData().getUser().getRoomNumber()).enqueue(new Callback<AlarmResponse>() {
                   @Override
                   public void onResponse(Call<AlarmResponse> call, Response<AlarmResponse> response) {
                       Toast.makeText(getContext(),"Alarm Has Been Setting",Toast.LENGTH_SHORT).show();
                       Fragment fragment = new DashboardFragment();
                       getFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
                   }

                   @Override
                   public void onFailure(Call<AlarmResponse> call, Throwable t) {
                        int b = 0;
                   }
               });

          }

        });

        return mViev;
    }
}
