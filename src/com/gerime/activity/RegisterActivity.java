package com.gerime.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;

import com.gerime.R;
import com.gerime.model.AccountMoviment;
import com.gerime.repository.EntityManager;

public class RegisterActivity extends Activity {
    AccountMoviment accountMoviment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loadData();
    }

    private void loadData() {
        accountMoviment = new AccountMoviment();
        try {
            long id = getIntent().getExtras().getLong("id");
            accountMoviment.setId(id);
            accountMoviment = EntityManager.getSqlAdapter().findFirst(
                    accountMoviment);
            ((EditText) findViewById(R.id.value)).setText(Float
                    .toString(accountMoviment.getValue()));
            ((EditText) findViewById(R.id.description)).setText(accountMoviment
                    .getDescription());
            ((EditText) findViewById(R.id.date)).setText(accountMoviment
                    .getDate());

            if (accountMoviment.isIn()) {
                ((Switch) findViewById(R.id.type)).setChecked(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        boolean result;
        switch (item.getItemId()) {
            case R.id.save:
                saveAccount();
                result = true;
                break;
            case R.id.remove:
                removeAccount();
                result = true;
                break;
            default:
                result = super.onMenuItemSelected(featureId, item);
        }
        return result;

    }

    private void removeAccount() {
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.confirme_remove_account))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EntityManager.getSqlAdapter().delete(accountMoviment);
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.no), null)
                .show();
    }

    private void saveAccount() {
        accountMoviment.setAccount(1);
        accountMoviment.setValue(Float
                .parseFloat(((EditText) findViewById(R.id.value)).getText()
                        .toString()));

        accountMoviment.setType(((Switch) findViewById(R.id.type))
                .isChecked() ? AccountMoviment.IN : AccountMoviment.OUT);

        accountMoviment
                .setDescription(((EditText) findViewById(R.id.description))
                        .getText().toString());
        accountMoviment.setDate(((EditText) findViewById(R.id.date))
                .getText().toString());
        EntityManager.getSqlAdapter().store(accountMoviment);
        finish();
    }
}
