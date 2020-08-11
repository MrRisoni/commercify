<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShopReviews extends AbstractMigration
{

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
