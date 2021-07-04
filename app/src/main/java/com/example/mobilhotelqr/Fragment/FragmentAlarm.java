package com.example.mobilhotelqr.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.mobilhotelqr.R;

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



                                month += 1;  // aylar dizi olarak 0'dan başladığı için 1 artırılarak yazıldı.
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
                                tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Seç", tpd);
                                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "İptal", tpd);
                                tpd.show();
                            }
                        }, year, month, dayOfMonth);

                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
                dpd.show();



         select_date.setOnClickListener(new View.OnClickListener() {
           @Override
         public void onClick(View view) {


                // buraya servis komutları yazılacak. Butona tıklayınca resepsiyona gönderilecek kodlar burada olacak.
               String deneme = date.getText().toString();
               retrofitProcess = ApiUtils.setAlarm();
               retrofitProcess.setAlarm(deneme,21).enqueue(new Callback<AlarmResponse>() {
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
