package com.gerime.activity;

import android.app.Activity;
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
		if (item.getItemId() == R.id.save) {

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
			return true;
		} else {
			return super.onMenuItemSelected(featureId, item);
		}

	}
}
