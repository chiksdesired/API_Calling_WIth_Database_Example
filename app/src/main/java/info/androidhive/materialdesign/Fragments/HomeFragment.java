package info.androidhive.materialdesign.Fragments;

/**
 * Created by Ravi on 29/07/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialdesign.Constant.APIInterface;
import info.androidhive.materialdesign.Constant.ApiClient;
import info.androidhive.materialdesign.Constant.Constant;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.adapter.AdapterContactList;
import info.androidhive.materialdesign.database.DatabaseHelper;
import info.androidhive.materialdesign.model.Contact;
import info.androidhive.materialdesign.model.ContactsItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private RecyclerView rvcontactList;
    private DatabaseHelper databaseHelper;
    List<ContactsItem> arrayList_contact = new ArrayList<>();
    APIInterface apiInterface;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        rvcontactList = (RecyclerView) rootView.findViewById(R.id.rvcontactList);
        apiInterface = ApiClient.getClient().create(APIInterface.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvcontactList.setLayoutManager(linearLayoutManager);

        databaseHelper = new DatabaseHelper(getActivity());
        try {

            databaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Inflate the layout for this fragment

        if (Constant.isNetworkAvailable(getActivity())) {
            GetContactList();
        }

        return rootView;


    }


    public void GetContactList() {
        Call<Contact> call = apiInterface.GetContacts();
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {

                arrayList_contact = response.body().getContacts();
                Log.e("size", "size" + arrayList_contact.size());

                AdapterContactList adapterWinnerList = new AdapterContactList(getActivity(), response.body());
                rvcontactList.setAdapter(adapterWinnerList);

                databaseHelper.openDataBase();

                for (int i = 0; i < arrayList_contact.size(); i++) {


                    databaseHelper.insertUserDataContact(arrayList_contact.get(i).getName(),
                            arrayList_contact.get(i).getEmail(), arrayList_contact.get(i).getPhone().getMobile());

                }
                databaseHelper.close();



                /*if(response.body().getContacts().size()>0)
                {
                    if(int i=0;i< response.body().getContacts().size();i++)
                    {

                    }
                }
                */


            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                call.cancel();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
