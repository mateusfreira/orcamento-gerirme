package com.gerime.activity;

import java.util.List;

import android.app.ListActivity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.gerime.R;
import com.gerime.model.AccountMoviment;
import com.gerime.repository.EntityManager;

public class DashboradActivity extends ListActivity implements
		SearchView.OnQueryTextListener {
	List<AccountMoviment> list;
	private SearchView mSearchView;

	@Override
	protected void onResume() {
		super.onResume();
		list = EntityManager.getSqlAdapter().findAll(AccountMoviment.class);
		prepareListView();
	}

	private void prepareListView() {
		float saldo = 0.00f;
		AccountMoviment amountMoviment = new AccountMoviment();
		amountMoviment.setDescription("Saldo");
		amountMoviment.setDate("1000");
		for (AccountMoviment am : list) {
			if (am.isIn()) {
				saldo += am.getValue();
			} else {
				saldo -= am.getValue();
			}
		}
		if (saldo < 0.00) {
			amountMoviment.setType(AccountMoviment.OUT);
			saldo = saldo * -1;
		} else {
			amountMoviment.setType(AccountMoviment.IN);
		}
		amountMoviment.setValue(saldo);
		list.add(amountMoviment);
		setListAdapter(new ArrayAdapter<AccountMoviment>(this,
				android.R.layout.simple_list_item_1, list));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		// setContentView(android.R.layout.activity_list_item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, RegisterActivity.class);

		intent.putExtra("id", list.get(position).getId());
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.dashborad_menu, menu);
		MenuItem searchItem = menu.findItem(R.id.action_search);
		mSearchView = (SearchView) searchItem.getActionView();
		setupSearchView(searchItem);

		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (item.getItemId() == R.id.register) {
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			return true;
		} else if (item.getItemId() == R.id.about) {
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			return true;
		}
		{
			return super.onMenuItemSelected(featureId, item);
		}

	}

	private void setupSearchView(MenuItem searchItem) {
		if (isAlwaysExpanded()) {
			mSearchView.setIconifiedByDefault(false);
		} else {
			searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
					| MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		}

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		if (searchManager != null) {
			List<SearchableInfo> searchables = searchManager
					.getSearchablesInGlobalSearch();

			SearchableInfo info = searchManager
					.getSearchableInfo(getComponentName());
			for (SearchableInfo inf : searchables) {
				if (inf.getSuggestAuthority() != null
						&& inf.getSuggestAuthority().startsWith("applications")) {
					info = inf;
				}
			}
			mSearchView.setSearchableInfo(info);
		}

		mSearchView.setOnQueryTextListener(this);

	}

	@Override
	public boolean onQueryTextChange(String newText) {
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		list = EntityManager.getSqlAdapter().findAll(AccountMoviment.class,
				"description like ?",
				new String[] { '%' + mSearchView.getQuery().toString() + '%' });
		prepareListView();
		return true;
	}

	public boolean onClose() {
		return false;
	}

	protected boolean isAlwaysExpanded() {
		return false;
	}
}
