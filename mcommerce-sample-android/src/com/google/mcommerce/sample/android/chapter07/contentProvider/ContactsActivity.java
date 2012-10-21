package com.google.mcommerce.sample.android.chapter07.contentProvider;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.OperationApplicationException;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.QuickContactBadge;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;

public class ContactsActivity extends ListActivity implements OnClickListener,
		LoaderManager.LoaderCallbacks<Cursor> {
	private static final String TAG = "ContactsActivity";
	static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
			Contacts._ID, // 0
			Contacts.DISPLAY_NAME, // 1
			Contacts.STARRED, // 2
			Contacts.TIMES_CONTACTED, // 3
			Contacts.CONTACT_PRESENCE, // 4
			Contacts.PHOTO_ID, // 5
			Contacts.LOOKUP_KEY, // 6
			Contacts.HAS_PHONE_NUMBER, // 7
	};

	static final int SUMMARY_ID_COLUMN_INDEX = 0;
	static final int SUMMARY_NAME_COLUMN_INDEX = 1;
	static final int SUMMARY_STARRED_COLUMN_INDEX = 2;
	static final int SUMMARY_TIMES_CONTACTED_COLUMN_INDEX = 3;
	static final int SUMMARY_PRESENCE_STATUS_COLUMN_INDEX = 4;
	static final int SUMMARY_PHOTO_ID_COLUMN_INDEX = 5;
	static final int SUMMARY_LOOKUP_KEY = 6;
	static final int SUMMARY_HAS_PHONE_COLUMN_INDEX = 7;

	private Button insertButton;

	private Button deleteButton;

	private ContactListItemAdapter adapter;

	private Button updateButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.c07_quick_contact);
		insertButton = (Button) findViewById(R.id.button_insert);
		insertButton.setOnClickListener(this);
		deleteButton = (Button) findViewById(R.id.button_delete);
		deleteButton.setOnClickListener(this);
		updateButton = (Button) findViewById(R.id.button_update);
		updateButton.setOnClickListener(this);
		adapter = new ContactListItemAdapter(this,
				R.layout.c07_quick_contact_item, null);
		setListAdapter(adapter);
		getLoaderManager().initLoader(0, null, this);
	}

	private final class ContactListItemAdapter extends ResourceCursorAdapter {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return super.getView(position, convertView, parent);
		}

		public ContactListItemAdapter(Context context, int layout, Cursor c) {
			super(context, layout, c);
		}

		// 记录被选择的联系人
		ArrayList<Long> state = new ArrayList<Long>();

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			final ContactListItemCache cache = (ContactListItemCache) view
					.getTag();
			// Set the name
			cursor.copyStringToBuffer(SUMMARY_NAME_COLUMN_INDEX,
					cache.nameBuffer);
			int size = cache.nameBuffer.sizeCopied;
			final Long id = cursor.getLong(SUMMARY_ID_COLUMN_INDEX);
			cache.selected
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							// TODO Auto-generated method stub
							if (isChecked) {
								log("checkedid" + id);
								state.add(id);
							} else {
								log("ucheckedid" + id);
								state.remove(id);
							}
						}

					});
			if (state.contains(id)) {
				cache.selected.setChecked(true);
			} else {
				cache.selected.setChecked(false);
			}
			cache.nameView.setText(cache.nameBuffer.data, 0, size);
			// 获得联系人的ID
			final long contactId = cursor.getLong(SUMMARY_ID_COLUMN_INDEX);
			// 获得联系人的lookup_key
			final String lookupKey = cursor.getString(SUMMARY_LOOKUP_KEY);
			// 将当前联系人与QuickContactBadge控件关联
			cache.photoView.assignContactUri(Contacts.getLookupUri(contactId,
					lookupKey));
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			View view = super.newView(context, cursor, parent);
			ContactListItemCache cache = new ContactListItemCache();
			cache.nameView = (TextView) view.findViewById(R.id.name);
			cache.photoView = (QuickContactBadge) view.findViewById(R.id.badge);
			cache.selected = (CheckBox) view.findViewById(R.id.selected);
			view.setTag(cache);
			return view;
		}
	}

	/**
	 * 首先向RawContacts.CONTENT_URI执行一个插入操作，目的是获取系统返回的rawContactId
	 * 这是后面插入data表的数据，只有执行空值插入，才能使插入的联系人在通讯录里可见
	 */
	public void insertContact() {
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		int rawContactInsertIndex = ops.size();
		// 首先向RawContacts.CONTENT_URI执行一个插入，目的是获取系统返回的rawContactId
		ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
				.withValue(RawContacts.ACCOUNT_TYPE, "google.com")
				.withValue(RawContacts.ACCOUNT_NAME, "leeivan2008@google.com")
				.build());
		// 往data表写入姓名数据
		ops.add(ContentProviderOperation
				.newInsert(Data.CONTENT_URI)
				.withValueBackReference(Data.RAW_CONTACT_ID,
						rawContactInsertIndex)
				.withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
				.withValue(StructuredName.DISPLAY_NAME, "Tom").build());
		// 往data表写入电话数据
		ops.add(ContentProviderOperation
				.newInsert(Data.CONTENT_URI)
				.withValueBackReference(Data.RAW_CONTACT_ID,
						rawContactInsertIndex)
				.withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
				.withValue(Phone.NUMBER, "12345678901")
				.withValue(Phone.TYPE, Phone.TYPE_MOBILE).build());
		// 往data表写入Email数据
		ops.add(ContentProviderOperation
				.newInsert(Data.CONTENT_URI)
				.withValueBackReference(Data.RAW_CONTACT_ID,
						rawContactInsertIndex)
				.withValue(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
				.withValue(Email.DATA, "tom@gmail.com")
				.withValue(Email.TYPE, Email.TYPE_WORK).build());
		try {
			getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	final static class ContactListItemCache {
		public TextView nameView;
		public QuickContactBadge photoView;
		public CheckBox selected;
		public CharArrayBuffer nameBuffer = new CharArrayBuffer(128);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_insert:
			insertContact();
			break;
		case R.id.button_delete:
			deleteContact();
			break;
		case R.id.button_update:
			updateContact();
			break;
		}
	}

	private void updateContact() {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		values.put(StructuredName.DISPLAY_NAME, "Rose");
		this.getBaseContext()
				.getContentResolver()
				.update(Data.CONTENT_URI, values,
						StructuredName.DISPLAY_NAME + "=?",
						new String[] { "Tom" });
	}

	private void deleteContact() {
		// TODO Auto-generated method stub
		String where = RawContacts.CONTACT_ID + " = ? ";
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		for (int i = 0; i < adapter.state.size(); i++) {
			log("delete" + adapter.state.get(i));
			ops.add(ContentProviderOperation
					.newDelete(RawContacts.CONTENT_URI)
					.withSelection(where,
							new String[] { adapter.state.get(i).toString() })
					.build());
		}
		try {
			getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
			// 清空state中的ID
			adapter.state.clear();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		// This is called when a new Loader needs to be created. This
		// sample only has one Loader, so we don't care about the ID.
		// First, pick the base URI to use depending on whether we are
		// currently filtering.
		Uri baseUri;
		// if (mCurFilter != null) {
		// baseUri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI,
		// Uri.encode(mCurFilter));
		// } else {
		baseUri = Contacts.CONTENT_URI;
		// }

		// Now create and return a CursorLoader that will take care of
		// creating a Cursor for the data being displayed.
		String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
				+ Contacts.HAS_PHONE_NUMBER + "=1) AND ("
				+ Contacts.DISPLAY_NAME + " != '' ))";
		// 根据本地的语言特征对字符串进行升序排序，意思是说对联系人名称进行升序排序
		String orderString = Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
		return new CursorLoader(this, baseUri, CONTACTS_SUMMARY_PROJECTION,
				select, null, orderString);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		adapter.swapCursor(arg1);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		adapter.swapCursor(null);
	}

	private void log(String msg) {
		Log.d(TAG, msg);
	}
}
