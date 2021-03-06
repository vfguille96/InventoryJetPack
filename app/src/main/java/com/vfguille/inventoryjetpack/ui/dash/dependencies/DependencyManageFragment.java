package com.vfguille.inventoryjetpack.ui.dash.dependencies;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.data.model.Dependency;
import com.vfguille.inventoryjetpack.ui.dash.DashBoardActivity;


public class DependencyManageFragment extends Fragment implements DependencyManageContract.View {

    public static final String TAG = "dependencyAddFragment";
    private EditText edShortName;
    private EditText edName;
    private EditText edDescription;
    private FloatingActionButton floatingActionButton;
    private Spinner spInventory;
    private DependencyManageContract.Presenter dependencyManagePresenter;
    private static int notId = 2;
    private static int notIdIntent = 2;

    // Métodos del contrato DependencyManageContract
    @Override
    public void setPresenter(DependencyManageContract.Presenter presenter) {
        this.dependencyManagePresenter = presenter;
    }

    @Override
    public void showError(String error) {
        //Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
        Snackbar.make(floatingActionButton, error, Snackbar.LENGTH_LONG)
                .setTextColor(getContext().getColor(R.color.colorPrimary))
                .setAnchorView(floatingActionButton)
                .show();
    }

    /**
     * Es llamado desde el Presenter después de realizar una de las acciones de insert/update y se muestra la lista.
     */
    @Override
    public void onSuccess() {
        //Un PendingIntent tiene un objeto en su interior que define lo que se quiere ejecutar.
        Intent intent = new Intent(getActivity(), DependencyActivity.class);
        intent.putExtra("NOTIFICATION", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Dependency.TAG, getDependency());
        intent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity() , notIdIntent++, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = buildNotification(pendingIntent);
        NotificationCompat.Builder builder2 = buildNotification();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

        // notificationId is a unique int for each notification that must be defined
        notificationManager.notify(1, builder2.build());
        notificationManager.notify(notId++, builder.build());
        Log.d("NOT", String.valueOf(notId));
        getActivity().onBackPressed();
    }

    private NotificationCompat.Builder buildNotification(PendingIntent pendingIntent) {
        return new NotificationCompat.Builder(getActivity(), getString(R.string.canal))
                .setSmallIcon(R.drawable.inventorylogo)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setContentTitle("Prueba notificaçao")
                .setContentText("Prueba notificaçao de dependency.")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Prueba notificaçao de dependency."))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                .setGroup("dependency2")
                .setAutoCancel(true);
    }


    /*
        Notificación padre para agrupar. Sin PendingIntent y
        ".setGroup("dependency2")
        .setGroupSummary(true)"
     */
    private NotificationCompat.Builder buildNotification() {
        return new NotificationCompat.Builder(getActivity(), getString(R.string.canal))
                .setSmallIcon(R.drawable.inventorylogo)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setContentTitle("Prueba notificaçao")
                .setContentText("Prueba notificaçao de dependency.")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Prueba notificaçao de dependency."))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                .setGroup("dependency2")
                .setGroupSummary(true)
                .setAutoCancel(true);
    }

    @Override
    public void showError(int errAddDependency) {
        //Toast.makeText(getContext(), getString(errAddDependency), Toast.LENGTH_LONG).show();
        Snackbar.make(floatingActionButton, errAddDependency, Snackbar.LENGTH_LONG)
                .setTextColor(getContext().getColor(R.color.colorPrimary))
                .setAnchorView(floatingActionButton)
                .show();

    }

    /**
     * Es llamado desde el Presenter después de comprobar que la dependencia es correcta.
     */
    @Override
    public void onSuccessValidate() {
        Dependency dependency = getDependency();
        if (getArguments() != null)
            dependencyManagePresenter.edit(dependency);
        else
            dependencyManagePresenter.add(dependency);
    }


    public static Fragment onNewInstance(Bundle bundle) {
        DependencyManageFragment fragment = new DependencyManageFragment();
        if (bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViewElements(view);

        Bundle bundle = getArguments();
        if (bundle != null)
            setDependencyInView(bundle);
    }

    private void initializeViewElements(@NonNull View view) {
        edDescription = view.findViewById(R.id.edDescription);
        edName = view.findViewById(R.id.edName);
        edShortName = view.findViewById(R.id.edShortName);
        spInventory = view.findViewById(R.id.spInventory);
        initializeFab();

    }

    private void setDependencyInView(Bundle bundle) {
        Dependency dependency = bundle.getParcelable(Dependency.TAG);
        edShortName.setEnabled(false);
        edShortName.setText(dependency.getShortName());
        edName.setText(dependency.getName());
        edDescription.setText(dependency.getDescription());
    }


    /**
     * Valida la dependencia. Añade o edita.
     */
    private void initializeFab() {
        floatingActionButton = getActivity().findViewById(R.id.fabSection);
        floatingActionButton.setImageResource(R.drawable.ic_done_black_24dp);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDependencyValid())
                    dependencyManagePresenter.validateDependency(getDependency());
            }
        });
    }

    /**
     * Recoge los datos de la vista y se crea una Dependencia.
     *
     * @return
     */
    private Dependency getDependency() {
        Dependency dependency = new Dependency();
        dependency.setName(edName.getText().toString());
        dependency.setShortName(edShortName.getText().toString());
        dependency.setInventory(spInventory.getSelectedItem().toString());
        dependency.setDescription(edDescription.getText().toString());
        return dependency;
    }

    /**
     * Comprueba las reglas de negocio del Modelo Dependency
     *
     * @return
     */
    private boolean isDependencyValid() {
        // RN1: campos no vacíos
        if (TextUtils.isEmpty(edShortName.getText().toString())) {
            showError(getString(R.string.errShortNameEmpty));
            return false;
        }

        if (TextUtils.isEmpty(edName.getText().toString())) {
            showError(getString(R.string.errNameEmpty));
            return false;
        }

        if (TextUtils.isEmpty(edDescription.getText().toString())) {
            showError(getString(R.string.errDescriptionEmpty));
            return false;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dependency_add, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}