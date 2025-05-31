
https://github.com/user-attachments/assets/edcfa7cf-13c2-442e-9f43-ebeb7df7aea5
# Student Management System

## 概要
本プロジェクトは、受講生とその受講コース情報を一元管理するためのWebアプリケーションです。管理者が受講生の基本情報およびコース履歴を登録・閲覧・更新・削除できるように設計されています。

## 動画
実際に動作している動画は以下になります。ぜひご覧ください。

https://github.com/user-attachments/assets/114d2343-6fba-4277-a61c-b59ae26218b1


## 仕様技術
- 言語: Java
- フレームワーク: Spring Boot
- ビルドツール: Gradle
- データベース: MySQL
- 開発環境: IntelliJ IDEA
- その他: Swagger（API仕様確認）

## 主な機能
- 受講生の基本情報（氏名、年齢、性別など）のCRUD操作
- 各受講生に紐づく複数コースの登録・更新・削除
- 受講生とコース情報の1対多構造の管理
- DTOとコンバーターによるデータ整合性の確保
- グローバル例外ハンドラーによるエラー管理とJSONレスポンス
- Swaggerを用いたAPI仕様確認

## DB構造

students(親テーブル）

・ id (PK,AUTO_INCREMENT)   
・ full_name(NOT NULL)                     
・kana_name(NOT NULL)                        
・nickname                                   
・email_address (NOT NULL)                   
・address                                    
・age                      
・sex                      
・remark                   
・is_deleted (DEFAULT 0)  

 students_courses（子テーブル）
               
・ id (PK)               
・student_id (FK)       
・course_name(NOT NULL) 
・start_date            
・end_date              

- students_coursesテーブルのstudent_id は students.id を参照（外部キー）
- ON DELETE CASCADE により、親レコード削除時に子レコードも連動して削除される。
- is_deleted は論理削除用フラグ（0: 有効、1: 削除済み）
- start_date, end_date は TIMESTAMP 型（NULL許容）

## 設計方針
- **三層アーキテクチャの採用**  
  アプリ全体を Controller層 / Service層 / Repository層 に分離し、それぞれが責任を持って処理を担当する構造とした。

- **DTO + コンバーターによるデータ統合**  
  異なる2つのテーブル（students・students_courses）から取得したデータをひとつにまとめて扱うため、DTOクラスと専用のコンバータークラスを
  実装。IDをもとにデータを結合し、ControllerやService層でのロジックを簡潔に保つよう配慮。

- **トランザクション制御の集約**  
  Service層にトランザクション制御を集約し、受講生とそのコース情報を一括で処理可能にすることで、整合性の高いデータ操作を実現。

- **グローバル例外ハンドラーの実装**  
  バリデーションエラー、DB例外、リクエスト不備、トランザクション失敗などを一括でハンドリングし、すべてのエラーを統一されたJSON形式で返す
  構成としている。

- **開発効率と品質向上の工夫**  
  SwaggerアノテーションでAPI仕様を明確化し、フロントエンドや外部連携に配慮。さらにログ出力を取り入れ、デバッグや障害調査の効率に配慮。
