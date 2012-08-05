package com.google.mcommerce.sample.android.chapter07;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.mcommerce.sample.android.R;

/**
 * @author ivan
 * 
 */
public class UserDictionaryActivity extends Activity implements OnClickListener {
	private static final String TAG = "UserDictionaryActivity";
	static final String[] mProjection = { UserDictionary.Words._ID,
			UserDictionary.Words.WORD, UserDictionary.Words.LOCALE };
	private EditText mSearchWord;

	private String mSortOrder = UserDictionary.Words.WORD + " ASC";

	private ListView mWordList;
	private Button mButtonInsert;
	private Button mButtonUpdate;
	private Button mButtonDelete;
	private Button mButtonSearch;
	private Cursor mCursor;
	private SimpleCursorAdapter mCursorAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.c07_basic_content_provider);
		mSearchWord = (EditText) findViewById(R.id.search_word);
		mButtonInsert = (Button) findViewById(R.id.button_insert);
		mButtonInsert.setOnClickListener(this);
		mButtonUpdate = (Button) findViewById(R.id.button_update);
		mButtonUpdate.setOnClickListener(this);
		mButtonDelete = (Button) findViewById(R.id.button_delete);
		mButtonDelete.setOnClickListener(this);
		mButtonSearch = (Button) findViewById(R.id.button_search);
		mButtonSearch.setOnClickListener(this);
		mWordList = (ListView) findViewById(R.id.listView);
		mCursor = getAllUserDictionary(null);
		if (null == mCursor) {
			log("获取数据失败");
		} else if (mCursor.getCount() < 1) {
			Toast.makeText(this, "没有数据，请插入数据", Toast.LENGTH_LONG).show();
		} else {
			// 定义需要显示的列
			String[] mWordListColumns = { UserDictionary.Words.WORD,
					UserDictionary.Words.LOCALE };

			// 定义显示每列的控件
			int[] mWordListItems = { R.id.dictWord, R.id.locale };

			// 创建一个Adapter对象
			mCursorAdapter = new SimpleCursorAdapter(getApplicationContext(),
					R.layout.c07_wordlistrow, mCursor, mWordListColumns,
					mWordListItems, 0);
			mCursorAdapter.notifyDataSetChanged();
			mWordList.setAdapter(mCursorAdapter);
		}
	}

	private Cursor getAllUserDictionary(String mSearchString) {
		String mSelectionClause;
		String[] mSelectionArgs = { "" };
		if (TextUtils.isEmpty(mSearchString)) {
			// 如果没有查询条件
			mSelectionClause = null;
			mSelectionArgs = null;
		} else {
			// 如果输入了查询条件
			mSelectionClause = UserDictionary.Words.WORD + " = ?";
			mSelectionArgs[0] = mSearchString;
		}
		// 定义一个查询，返回一个游标对象
		Cursor mCursor = getContentResolver().query(
				UserDictionary.Words.CONTENT_URI, mProjection,
				mSelectionClause, mSelectionArgs, mSortOrder);
		return mCursor;
	}

	private void log(String msg) {
		Log.d(TAG, msg);
	}

	@Override
	public void onClick(View v) {
		String mSelectionClause;
		String[] mSelectionArgs = new String[1];
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_insert:
			// 定义插入操作后的返回值
			Uri mNewUri;
			// 定义ContentValuse对象
			ContentValues mNewValues = new ContentValues();
			// 使用ContentValues的put方法为键值对赋值
			mNewValues.put(UserDictionary.Words.LOCALE, "en_US");
			mNewValues.put(UserDictionary.Words.WORD, "insert");
			// 第一个参数是URI，第二个为ContentValue
			mNewUri = getContentResolver().insert(
					UserDictionary.Words.CONTENT_URI, mNewValues);
			mCursor = getAllUserDictionary(null);
			mCursorAdapter.changeCursor(mCursor);
			Toast.makeText(this, "插入数据为" + ContentUris.parseId(mNewUri),
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.button_update:
			// 定义ContentValues包含需要更新的值
			ContentValues mUpdateValues = new ContentValues();
			// 定义更新行的条件
			mSelectionClause = UserDictionary.Words.WORD + " LIKE ?";
			mSelectionArgs[0] = "in%";
			// 定义更新行的数目
			int mRowsUpdated;
			// 为ContentValue赋值
			mUpdateValues.put(UserDictionary.Words.WORD, "update");

			mRowsUpdated = getContentResolver().update(
					UserDictionary.Words.CONTENT_URI, mUpdateValues,
					mSelectionClause, mSelectionArgs);
			mCursor = getAllUserDictionary(null);
			mCursorAdapter.changeCursor(mCursor);
			Toast.makeText(this, "更新行数为" + mRowsUpdated, Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.button_delete:
			// 定义删除行的条件
			mSelectionClause = UserDictionary.Words.WORD + " LIKE ?";
			mSelectionArgs[0] = "in%";
			// 定义删除的行数
			int mRowsDeleted;
			// 根据条件删除行
			mRowsDeleted = getContentResolver().delete(
					UserDictionary.Words.CONTENT_URI, // the user dictionary
														// content URI
					mSelectionClause, // the column to select on
					mSelectionArgs // the value to compare to
					);
			mCursor = getAllUserDictionary(null);
			mCursorAdapter.changeCursor(mCursor);
			Toast.makeText(this, "删除行数为" + mRowsDeleted, Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.button_search:
			String mSearchString = mSearchWord.getText().toString();
			mCursor = getAllUserDictionary(mSearchString);
			mCursorAdapter.changeCursor(mCursor);
		}
	}
}
