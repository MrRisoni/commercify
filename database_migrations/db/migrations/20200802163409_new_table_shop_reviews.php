<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShopReviews extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        $shopsReviews = $this->table('shop_reviews', ['signed' => false]);
        $shopsReviews->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('user_id', 'biginteger', ['signed' => false])
            ->addColumn('stars', 'decimal', ['precision' => 2,'scale'=>1])
            ->addColumn('comment', 'string', ['limit' => 255])
            ->addColumn('created', 'datetime')
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('user_id', 'users', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
